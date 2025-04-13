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

// Function to set authentication status
export const updateAuthStatus = () => {
  isAuthenticated.value = sessionStorage.getItem('isAuthenticated') === 'true'
}
