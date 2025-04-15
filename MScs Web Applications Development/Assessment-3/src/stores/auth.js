import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAuthStore = defineStore('auth', () => {
  // Reactive state
  const isAuthenticated = ref(false)
  const userRole = ref('')

  // Actions
  const setAuthStatus = (status) => {
    isAuthenticated.value = status
    sessionStorage.setItem('isAuthenticated', status)
  }

  const getAuthStatus = () => {
    isAuthenticated.value = sessionStorage.getItem('isAuthenticated') === 'true'
    return isAuthenticated.value
  }

  const setUserRole = (role) => {
    userRole.value = role
    sessionStorage.setItem('userRole', role)
  }

  const getUserRole = () => {
    userRole.value = sessionStorage.getItem('userRole') || ''
    return userRole.value
  }

  // Logout function to clear authentication state and user role from session storage
  const logout = () => {
    isAuthenticated.value = false
    userRole.value = ''
    sessionStorage.removeItem('isAuthenticated')
    sessionStorage.removeItem('userRole')
  }

  // Restore state on initialization
  getAuthStatus()
  getUserRole()

  return {
    isAuthenticated,
    userRole,
    setAuthStatus,
    getAuthStatus,
    setUserRole,
    getUserRole,
    logout,
  }
})
