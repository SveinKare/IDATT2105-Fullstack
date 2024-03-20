import {defineStore} from "pinia";
import {useStorage} from "@vueuse/core";


export const useFormStore = defineStore({
    id:"formStore",
    state: () => ({
       name: useStorage("name", "", sessionStorage),
       email: useStorage("email", "", sessionStorage)
    }),
    actions: {
        updateName(name) {
            this.name = name;
        },
        updateEmail(email) {
            this.email = email;
        }
    }
})