<script setup lang="ts">
import MyPageTabComponent from '@/components/mypage/MyPageTab.vue'
</script>

<template>
  <v-expand-transition>
    <v-main>
      <v-container fluid class="pa-0">
        <MyPageTabComponent/>
        <router-view v-slot="{ Component }">
          <component :is="Component" :key="$route.fullPath" />
        </router-view>
      </v-container>
    </v-main>
  </v-expand-transition>
</template>

