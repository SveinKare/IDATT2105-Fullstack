<template>
  <div class="login-container">
    <h1>Login</h1>
    <form class="login-form" @submit.prevent="submit()">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username">
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password">
      <input type="submit" value="Submit">
    </form>
    <div class="register-container">
      <p>Don't have an account?</p>
      <RouterLink class="link" to="/register">Register</RouterLink>
    </div>
  </div>
</template>

<script setup>
import {useUserStore} from "@/stores/userStore.js";
import { useRouter } from 'vue-router'
import { getToken } from "@/utils/auth.js";

let username = defineModel("username", { type: String, default: "" });
let password = defineModel("password", { type: String, default: "" });

const userStore = useUserStore();
const router = useRouter();

async function submit() {
    const res = getToken(username.value, password.value).then((res) => {
      userStore.login(username.value, res.data);
      router.push('/');
    }).catch((error) => {
      alert('Login failed. Please try again.');
    });
}


</script>

<style scoped>
/* Your component styles go here */
</style>