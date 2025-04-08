<script setup>
import { RouterView } from 'vue-router'
import NavHeader from './components/NavHeader.vue'
</script>

<template>
  <div class="main container">
    <header>
      <NavHeader />
    </header>
    <main class="main-box container mt-5">
      <div class="col-md-8 offset-md-2">
        <router-view />
      </div>
    </main>
  </div>
</template>

<style scoped></style>
