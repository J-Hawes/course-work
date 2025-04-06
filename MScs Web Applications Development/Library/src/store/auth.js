import { ref } from 'vue'

export const isAuthenticated = ref(false)

export const updateAuthStatus = () => {
  isAuthenticated.value = sessionStorage.getItem('isAuthenticated') === 'true'
  console.log('Auth status updated:', isAuthenticated.value)
}
