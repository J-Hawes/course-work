<template>
  <div class="row">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <h1 class="text-center">Sign In</h1>
        <div class="row">
          <div class="col-6 col-md-6">
            <p><input type="text" class="form-control" placeholder="Email" v-model="email" /></p>
          </div>
          <div class="col-6 col-md-6">
            <p>
              <input
                type="password"
                class="form-control"
                placeholder="Password"
                v-model="password"
              />
            </p>
          </div>
          <div class="text-center col-md-12">
            <p><button class="btn btn-primary" @click="signin">Sign in via Firebase</button></p>
            <p>Don't have an account?</p>
            <button class="btn btn-primary" @click="register">Register with Firebase</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { getAuth, signInWithEmailAndPassword } from 'firebase/auth'
import { doc, getDoc } from 'firebase/firestore'
import { useRouter } from 'vue-router'
import { updateAuthStatus } from '../store/auth'
import { db } from '@/main'

const email = ref('')
const password = ref('')
const router = useRouter()
const auth = getAuth()

const signin = () => {
  signInWithEmailAndPassword(auth, email.value, password.value)
    .then(async (data) => {
      sessionStorage.setItem('isAuthenticated', true)
      console.log(auth.currentUser) //To check the current User signed in
      updateAuthStatus()
      const userDoc = await getDoc(doc(db, 'users', data.user.uid))
      console.log('User ID:', data.user.uid)
      if (userDoc.exists()) {
        const userData = userDoc.data()
        console.log('User Role:', userData.role)
      } else {
        console.log('No such document!')
      }
      router.push('/')
    })
    .catch((error) => {
      console.log(error)
    })
}

const register = () => {
  router.push({ name: 'FireRegister' })
}
</script>
