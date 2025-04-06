<template>
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h1 class="text-center">User Information Form</h1>
      <form @submit.prevent="submitForm">
        <div class="mb-3">
          <label for="username" class="form-label">Username</label>
          <input type="text" class="form-control" id="username" v-model="formData.username" />
          <div v-if="errors.username" class="text-danger">{{ errors.username }}</div>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input type="password" class="form-control" id="password" v-model="formData.password" />
          <div v-if="errors.password" class="text-danger">{{ errors.password }}</div>
          <div v-if="success.password" class="text-success">{{ success.password }}</div>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-primary me-2">Submit</button>
          <button type="button" class="btn btn-secondary" @click="clearForm">Clear</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { updateAuthStatus } from '../store/auth'
import { ref } from 'vue'

const router = useRouter()

const formData = ref({
  username: '',
  password: '',
})

const submitForm = () => {
  validateLogin(true)
  if (!errors.value.username && !errors.value.password) {
    sessionStorage.setItem('isAuthenticated', true)
    success.value.password = 'Login successful'
    console.log('isAuthenticated', sessionStorage.getItem('isAuthenticated'))
    updateAuthStatus()
    router.push({ name: 'Home' })
  }
}

const clearForm = () => {
  formData.value = {
    username: '',
    password: '',
  }
}

const success = ref({
  password: null,
})

const errors = ref({
  username: null,
  password: null,
})

const validateLogin = (blur) => {
  if (formData.value.username == 'testing' && formData.value.password == '12345678') return true
  else {
    if (blur) {
      errors.value.username = 'Invalid username or password'
      errors.value.password = 'Invalid username or password'
    }
    return false
  }
}
</script>

<style scoped></style>
