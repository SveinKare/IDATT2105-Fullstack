<script setup>
import {useFormStore} from "@/stores/formStore.js";
import {ref} from 'vue';

const formStore = useFormStore();
const userName = ref(formStore.name);
const email = ref(formStore.email);
const message = ref("");

async function submit() {
  const res = await fetch("http://localhost:3000/message",
      {
        method:"GET",
        headers:{
          "Content-Type": "application/json",
        }
      }).then(res => res.json());
  alert(res.message);
  formStore.updateName(userName.value);
  formStore.updateEmail(email.value);
  message.value = "";
}

</script>

<template>
  <div class="calc-view">
      <form @submit.prevent="submit()" class="form-container">
          <label for="name" class="label">Name</label>
          <input v-model="userName" id="name" type="text" class="form-input" required>
          <label for="email" class="label">E-mail</label>
          <input v-model="email" id="email" type="email" class="form-input" required>
          <label for="message" class="label">Message</label>
          <textarea v-model="message" id="message" class="form-input" required></textarea>
          <button id="submit-button" :disabled="!email.length || !userName.length || !message.length">Submit</button>
      </form>
  </div>
</template>


<style scoped>
#message {
  resize: none;
}
.form-input {
  width: auto;
  margin-left:5px;
  margin-right: 5px;
  margin-bottom: 10px;
  height: 30px;
  background-color: #8689AC;
  font-size: large;
  font-family: 'Oxygen';
}
.label {
  margin-left: 5px;
}
#submit-button {
  background-color: #587099;
  -webkit-text-fill-color: #101116;
  font-size: large;
  margin-top:auto;
  width: 100px;
}
#submit-button:disabled {
  background-color: rgba(88, 112, 153, 0.3);
  -webkit-text-fill-color: rgba(16, 17, 22, 0.3);
}
</style>