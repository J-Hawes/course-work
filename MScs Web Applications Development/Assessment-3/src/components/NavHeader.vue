<template>
  <div class="container">
    <header class="d-flex justify-content-center py-3">
      <ul class="nav nav-pills">
        <li class="nav-item">
          <router-link to="/" class="nav-link" active-class="active" aria-current="page"
            >Home</router-link
          >
        </li>
        <li class="nav-item">
          <router-link to="/about" class="nav-link" active-class="active">About</router-link>
        </li>
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link to="/login" class="nav-link" active-class="active">Login</router-link>
        </li>
        <!--
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link to="/firelogin" class="nav-link" active-class="active"
            >Firebase Login</router-link
          >
        </li> -->
        <li class="nav-item" v-else>
          <a href="#" class="nav-link" @click.prevent="logout">Logout</a>
        </li>
      </ul>
    </header>
  </div>
</template>

<script setup>
import { isAuthenticated, updateAuthStatus } from '../stores/auth'
import { useRouter } from 'vue-router'

const router = useRouter()

updateAuthStatus()
console.log('isAuthenticated', isAuthenticated.value)
const logout = () => {
  sessionStorage.removeItem('isAuthenticated')
  isAuthenticated.value = false
  router.push({ name: 'Home' })
}
</script>
