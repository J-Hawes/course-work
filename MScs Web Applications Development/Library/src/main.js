import 'bootstrap/dist/css/bootstrap.min.css'
//import './style.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import PrimeVue from 'primevue/config'
// import Aura from '@primevue/themes/aura' //dark theme

import { initializeApp } from 'firebase/app'
import { getFirestore } from 'firebase/firestore'

// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: 'AIzaSyBYk8HxrAkwa_8XuhLcJpXhjQMj9zyIa44',
  authDomain: 'mscs-webdev-library.firebaseapp.com',
  projectId: 'mscs-webdev-library',
  storageBucket: 'mscs-webdev-library.firebasestorage.app',
  messagingSenderId: '946537386221',
  appId: '1:946537386221:web:099f992fcd96d24c447ce4',
}

// Initialize Firebase

const db = getFirestore(initializeApp(firebaseConfig))
export { db }

sessionStorage.setItem('isAuthenticated', false)

const app = createApp(App)
// app.use(PrimeVue, {
//   theme: { preset: Aura },
// })
app.use(PrimeVue) //use vue without theme
app.use(router)
app.mount('#app')
