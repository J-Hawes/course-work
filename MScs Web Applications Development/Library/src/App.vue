<script setup>
//import JSON from './components/JSON.vue'
//import LoginForm from './views/HomeView.vue'
import BHeader from './components/BHeader.vue'
</script>

<template>
  <div class="main container">
    <header>
      <BHeader />
    </header>
    <main class="main-box container mt-5">
      <div class="col-md-8 offset-md-2">
        <!-- <JSON /> -->
        <!-- <LoginForm /> -->
        <router-view />
      </div>
    </main>
  </div>
</template>
