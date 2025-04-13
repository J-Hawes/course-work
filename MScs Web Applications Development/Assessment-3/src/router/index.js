import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomeView,
    },
    {
      path: '/health',
      name: 'Health',
      component: () => import('../views/HealthResources.vue'),
    },
    {
      path: '/services',
      name: 'Services',
      component: () => import('../views/ServicesDirectory.vue'),
    },
    {
      path: '/education',
      name: 'Education',
      component: () => import('../views/EducationResources.vue'),
    },
    {
      path: '/community',
      name: 'Community',
      component: () => import('../views/CommunityForum.vue'),
    },
    {
      path: '/additional',
      name: 'Additional',
      component: () => import('../views/AdditionalResources.vue'),
    },
    {
      path: '/about',
      name: 'About',
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginView,
    },
    {
      path: '/register',
      name: 'Register',
      component: () => import('../views/RegisterView.vue'),
    },
    {
      path: '/user',
      name: 'User',
      component: () => import('../views/UserDashboard.vue'),
    },
  ],
  // This option is used to scroll to the top of the page when navigating to a new route
  // and to scroll to the saved position when navigating back
  // or forward in the history stack.
  scrollBehavior(to, from, savedPosition) {
    if (to.hash) {
      return {
        el: to.hash,
        behavior: 'smooth',
      }
    } else if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  },
})

export default router
