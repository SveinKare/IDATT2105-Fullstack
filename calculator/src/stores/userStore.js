import { defineStore } from 'pinia';
import {useStorage} from "@vueuse/core";

export const useUserStore = defineStore('user', {
  state: () => ({
    user: useStorage("user", null, sessionStorage),
    token: useStorage("token", null, sessionStorage),
    loggedIn: useStorage("loggedIn", null, sessionStorage)
  }),

  getters: {
    // Define your getters here
    getUser: (state) => state.user,
    getLoggedIn: (state) => state.loggedIn,
  },

  actions: {
    // Define your actions here
    login(user, token) {
      this.user = user;
      this.token = token;
      this.loggedIn = true;
    },
    logout() {
      this.user = null;
      this.loggedIn = false;
      this.token = null;
    }
  },
});