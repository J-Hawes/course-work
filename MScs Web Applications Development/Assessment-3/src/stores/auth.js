import { ref } from 'vue'
// import { defineStore } from 'pinia'

// export const isAuthenticated = defineStore('isAuthenticated', () => {
//   const isAuthenticated = ref(sessionStorage.getItem('isAuthenticated') === 'true')
//   const setAuthStatus = (status) => {
//     isAuthenticated.value = status
//     sessionStorage.setItem('isAuthenticated', status)
//   }

//   return { isAuthenticated, setAuthStatus }
// })

export const isAuthenticated = ref(false)
export const userRole = ref('')

// Function to set authentication status
export const setAuthStatus = () => {
  isAuthenticated.value = sessionStorage.getItem('isAuthenticated') === 'true'
}

// Function to get role status
export const getUserRole = () => {
  userRole.value = sessionStorage.getItem('userRole') || ''
  return userRole.value
}

// Function to set role status
export const setUserRole = (role) => {
  userRole.value = role
  sessionStorage.setItem('userRole', role)
}
