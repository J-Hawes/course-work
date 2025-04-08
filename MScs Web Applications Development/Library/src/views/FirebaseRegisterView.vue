<template>
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h1 class="text-center mb-4">Register an account</h1>
      <div class="row">
        <div class="col-6 col-md-6 mb-3">
          <input type="text" class="form-control" placeholder="Email" v-model="email" />
        </div>
        <div class="col-6 col-md-6 mb-3">
          <input type="password" class="form-control" placeholder="Password" v-model="password" />
        </div>
        <div class="text-center mt-4">
          <button class="btn btn-primary" @click="register">Register with Firebase</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { getAuth, createUserWithEmailAndPassword } from 'firebase/auth'
import { doc, setDoc } from 'firebase/firestore'
import { useRouter } from 'vue-router'
import db from '../Firebase/init.js'

const email = ref('')
const password = ref('')
const router = useRouter()
const auth = getAuth()

const register = () => {
  createUserWithEmailAndPassword(auth, email.value, password.value)
    .then((data) => {
      console.log('Firebase Register successful!')
      const role = email.value === 'admin@gmail.com' ? 'admin' : 'user'
      setDoc(doc(db, 'users', data.user.uid), {
        email: email.value,
        role: role,
      })

      router.push({ name: 'FireLogin' })
    })
    .catch((error) => {
      console.log(error.code)
    })
}
</script>
