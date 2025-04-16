<script setup>
import { RouterView } from 'vue-router'
import NavHeader from './components/NavHeader.vue'
</script>

<template>
  <div class="main container-fluid">
    <header>
      <NavHeader />
    </header>
    <main class="main-box container-fluid">
      <div class="col-md-12">
        <router-view />
      </div>
    </main>
  </div>
</template>

<style scoped></style>
