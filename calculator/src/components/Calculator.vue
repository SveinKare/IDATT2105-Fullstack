<script setup>
import {useUserStore} from "@/stores/userStore.js";
import { reactive } from "vue";
import { evaluate } from "@/utils/calcUtils.js";
import { getHistory } from "@/utils/calcUtils.js";
import { useRouter } from "vue-router";

let text = defineModel({type:String, default:""});
let log = reactive([]);
let textIsAnswer = false;

const userStore = useUserStore();
const router = useRouter();

function updateField(symbol) {
  let numbers = /[0-9]/;
  let operators = /[*/+-.]/;
  let lastSymbol = text.value.at(-1);
  if(numbers.test(symbol) || (!operators.test(lastSymbol))) {
    if (textIsAnswer) {
      textIsAnswer = false;
    }
    let invalidStartOperators = /[*/\.]/;
    if (!(text.value === "" && invalidStartOperators.test(symbol))) {
      text.value = text.value + symbol;
    }
  }
}
function backSpace() {
  if (textIsAnswer) {
    clearAll();
    textIsAnswer = false;
  } else {
    text.value = text.value.slice(0, -1);
  }
}
function clearAll() {
  text.value = "";
}
async function calculate() {
  if (!userStore.loggedIn) {
    alert("You need to be logged in to use the calculator");
    return;
  }
  const res = await evaluate(text.value, userStore.getUser)
  .then((res) => text.value = res.data)
  .catch((error) => {
    if (error.response.status === 403) {
      alert("Session expired. Please log in again.");
      userStore.logout();
      router.push("/login");
    } else {
      text.value += " = Undefined";
    }
  });

  let logItem = text.value;
  const newItem = {
    id:log.length + 1,
    text:logItem
  }
  textIsAnswer = true;
  log.push(newItem);
}
window.addEventListener('keydown', (event) => {
  let numbers = /[0-9]/;
  let operators = /[*/+\-.]/;
  if (numbers.test(event.key) || operators.test(event.key)) {
    updateField(event.key);
  } else if (event.key === 'Backspace') {
    backSpace();
  }
});

async function fetchHistory() {
  if (!userStore.loggedIn) {
    alert("You need to be logged in to use the calculator");
    return;
  }
  let data = [];
  getHistory(userStore.getUser)
  .then(res => {
    data = res.data;
    log.splice(0, log.length);
    for (let i = 0; i < data.length; i++) {
      const newItem = {
        id: log.length + 1,
        text: data[i]
      }
      log.push(newItem);
    }
  }).catch(expiredSession);
}

function expiredSession(error) {
  if (error.response.status === 403) {
    alert("Session expired. Please log in again.");
    userStore.logout();
    router.push("/login");
  } else {
    alert("Error fetching history");
  }
}
</script>

<template>
  <div id="container">
    <div id="calculator_interface">
      <div id="textFieldContainer">
        <input id="calcDisplay" class=text-field type="text" v-model="text" readonly="readonly">
      </div>
      <div id="buttons">
        <div class="row">
          <button class="calcButton" @click="updateField('+')">+</button>
          <button class="calcButton" @click="updateField('-')">-</button>
          <button class="calcButton" @click="updateField('*')">*</button>
          <button id="backspace" class="calcButton" @click="backSpace()">&lt</button>
        </div>
        <div class="row">
          <button id="button1" class="calcButton" @click="updateField(1)">1</button>
          <button class="calcButton" @click="updateField(2)">2</button>
          <button class="calcButton" @click="updateField(3)">3</button>
          <button id="clear" class="calcButton" @click="clearAll()">C</button>
        </div>
        <div class="row">
          <button class="calcButton" @click="updateField(4)">4</button>
          <button class="calcButton" @click="updateField(5)">5</button>
          <button class="calcButton" @click="updateField(6)">6</button>
          <button class="calcButton" @click="updateField('/')">/</button>
        </div>
        <div class="row">
          <button class="calcButton" @click="updateField(7)">7</button>
          <button class="calcButton" @click="updateField(8)">8</button>
          <button class="calcButton" @click="updateField(9)">9</button>
          <button class="calcButton"></button>
        </div>
        <div class="row">
          <button class="calcButton" ></button>
          <button class="calcButton" @click="updateField(0)">0</button>
          <button class="calcButton" @click="updateField('.')">.</button>
          <button id="equals" class="calcButton" @click="calculate()">=</button>
        </div>
      </div>
    </div>
    <div id="log-container">
      <ul class="log">
        <li v-for="item in log" :key="item.id">{{item.text}}</li>
      </ul>
      <button @click="fetchHistory()">Fetch history</button>
    </div>
  </div>
</template>


<style scoped>

</style>@/utils/calcUtils.js