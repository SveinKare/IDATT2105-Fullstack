<template>
  <div>
    <h1>Register</h1>
    <form @submit.prevent="submit()">
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="username">
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="password">
      <label for="confirmPassword">Confirm Password:</label>
      <input type="password" id="confirmPassword" v-model="confirmPassword">
      <input type="submit" value="Submit">
    </form>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

let username = defineModel("username", { type: String, default: "" });
let password = defineModel("password", { type: String, default: "" });
let confirmPassword = defineModel("confirmPassword", { type: String, default: "" });

const router = useRouter();

async function submit() {
  if (password.value !== confirmPassword.value) {
    alert("Passwords do not match");
    password.value = "";
    confirmPassword.value = "";
    return;
  } else {
    const res = await fetch("http://localhost:8080/user/register",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username: username.value, password: password.value })
      });
    if (res.ok) {
      router.push('/login');
    } else {
      alert('Registration failed. Please try again.');
    }
  }
}
</script>

<style scoped>
/* Your component-specific styles go here */
</style>