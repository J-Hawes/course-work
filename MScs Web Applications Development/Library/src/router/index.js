import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
//import LoginView from '../views/LoginView.vue'
import FirebaseSigninView from '@/views/FirebaseSigninView.vue'
import FirebaseRegisterView from '@/views/FirebaseRegisterView.vue'

const requireAuth = (to, from, next) => {
  const isAuthenticated = sessionStorage.getItem('isAuthenticated') === 'true'
  if (!isAuthenticated) {
    next({ name: 'FireLogin' })
  } else {
    next()
  }
}

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/about',
    name: 'About',
    component: AboutView,
    beforeEnter: requireAuth,
  },
  {
    path: '/firelogin',
    name: 'FireLogin',
    component: FirebaseSigninView,
  },
  {
    path: '/fireregister',
    name: 'FireRegister',
    component: FirebaseRegisterView,
  },
  // {
  //   path: '/login',
  //   name: 'Login',
  //   component: LoginView,
  // },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
