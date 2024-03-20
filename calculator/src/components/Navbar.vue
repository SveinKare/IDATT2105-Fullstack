<script setup>
import { useUserStore } from "@/stores/userStore.js";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const router = useRouter();


function logout() {
  userStore.logout();
  router.push("/login");
}

</script>

<template>
  <div class="navbar">
    <RouterLink to="/" class="link">Calculator</RouterLink>
    <RouterLink to="/feedback" class="link">Feedback</RouterLink>
    <RouterLink v-if="!userStore.getLoggedIn" to="/login" class="login link">Login</RouterLink>
    <button v-else class="login link" @click="logout()">Logout</button>
  </div>
</template>


<style scoped>

</style>