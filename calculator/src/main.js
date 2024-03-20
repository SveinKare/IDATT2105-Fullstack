import './assets/main.css'

import { createRouter, createWebHistory} from "vue-router";
import { createApp } from 'vue'
import App from './App.vue'
import CalculatorView from "@/views/CalculatorView.vue";
import FeedbackView from "@/views/FeedbackView.vue";
import LoginView from './views/LoginView.vue';
import RegisterView from './views/RegisterView.vue';
import {createPinia} from "pinia";


const router = createRouter({
    history:createWebHistory(),
    routes:[
        {path:"/", component:CalculatorView},
        {path:"/feedback", component:FeedbackView},
        {path:"/login", component:LoginView},
        {path:"/register", component:RegisterView}
    ]
});
const pinia = createPinia();


const app = createApp(App);
app.use(router);
app.use(pinia);
app.mount('#app');

