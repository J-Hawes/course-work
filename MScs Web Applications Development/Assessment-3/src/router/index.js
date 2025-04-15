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
      component: () => import('../views/HealthResourcesView.vue'),
    },
    {
      path: '/services',
      name: 'Services',
      component: () => import('../views/ServicesDirectoryView.vue'),
    },
    {
      path: '/education',
      name: 'Education',
      component: () => import('../views/EducationResourcesView.vue'),
    },
    {
      path: '/community',
      name: 'Community',
      component: () => import('../views/CommunityForumView.vue'),
      // This route is protected and requires authentication
      meta: { requiresAuth: true },
    },
    {
      path: '/additional',
      name: 'Additional',
      component: () => import('../views/AdditionalResourcesView.vue'),
    },
    {
      path: '/about',
      name: 'About',
      component: () => import('../views/AboutUsView.vue'),
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
      path: '/admin',
      name: 'Admin',
      component: () => import('../views/AdminDashboardView.vue'),
      // This route is protected and requires authentication
      meta: { requiresAuth: true },
    },
    {
      path: '/user',
      name: 'User',
      component: () => import('../views/UserDashboardView.vue'),
      // This route is protected and requires authentication
      meta: { requiresAuth: true },
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
