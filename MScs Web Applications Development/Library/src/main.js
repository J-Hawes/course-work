import 'bootstrap/dist/css/bootstrap.min.css'
//import './style.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import PrimeVue from 'primevue/config'
// import Aura from '@primevue/themes/aura' //dark theme

sessionStorage.setItem('isAuthenticated', false)

const app = createApp(App)

// app.use(PrimeVue, {
//   theme: { preset: Aura },
// })

app.use(PrimeVue) //use vue without theme
app.use(router)
app.mount('#app')
