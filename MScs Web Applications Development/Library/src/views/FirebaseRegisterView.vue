<template>
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h1 class="text-center">Register an account</h1>
      <div class="row">
        <div class="col-6 col-md-6">
          <p><input type="text" class="form-control" placeholder="Email" v-model="email" /></p>
        </div>
        <div class="col-6 col-md-6">
          <p>
            <input type="password" class="form-control" placeholder="Password" v-model="password" />
          </p>
        </div>
        <div class="text-center">
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
import { db } from '@/main'

const email = ref('')
const password = ref('')
const router = useRouter()
const auth = getAuth()

const register = () => {
  createUserWithEmailAndPassword(auth, email.value, password.value)
    .then((data) => {
      console.log('Firebase Register successful!')
      const role = email.value === 'admin@gmail.com' ? 'admin' : 'user'
      console.log('User Role:', role)
      console.log(db)
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
