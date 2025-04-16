<template>
  <div class="row mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <h1 class="text-center mb-4">Sign in with Firebase</h1>
        <div class="row">
          <div class="col-6 col-md-6 mb-3">
            <input type="text" class="form-control" placeholder="Email" v-model="email" />
          </div>
          <div class="col-6 col-md-6 mb-3">
            <input type="password" class="form-control" placeholder="Password" v-model="password" />
          </div>
          <div class="text-center col-md-12 mt-4">
            <button class="btn btn-primary" @click="signin">Sign in</button>
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
import { useRouter } from 'vue-router'
import { useFirebaseAuth } from 'vuefire'
import { doc, getDoc } from 'firebase/firestore'
import { signInWithEmailAndPassword } from 'firebase/auth'
import db from '../firebase/init.js'
import { useAuthStore } from '@/stores/auth.js'

const email = ref('')
const password = ref('')
const router = useRouter()
const authStore = useAuthStore()
const auth = useFirebaseAuth()

// Sign in with Firebase
const signin = async () => {
  try {
    const userCredential = await signInWithEmailAndPassword(auth, email.value, password.value)

    // Get the signed-in user's UID
    const userId = userCredential.user.uid

    // Bind the user's Firestore document to a reactive variable
    const userDocRef = doc(db, 'users', userId)
    const userDoc = await getDoc(userDocRef)

    // Check if the document exists, retrieve the user's role and store it in session storage
    if (userDoc.exists()) {
      const role = userDoc.data().role
      authStore.setUserRole(role)
    } else {
      console.log('No such document!')
    }

    // Update authentication status
    authStore.setAuthStatus(true)

    // Redirect to the home page after successful sign-in
    router.push('/')
  } catch (error) {
    console.log('Error signing in: ', error)
  }
}

// Function to redirect to the registration page
const register = () => {
  router.push({ name: 'Register' })
}
</script>
