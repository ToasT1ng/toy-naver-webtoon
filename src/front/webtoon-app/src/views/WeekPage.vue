<script setup lang="ts">
import WeekPageTab from '@/components/webtoon/WeekPageTab.vue'
</script>

<template>
  <v-expand-transition>
    <v-main>
      <v-container fluid class="pa-0">
        <WeekPageTab />
        <router-view v-slot="{ Component }">
          <component :is="Component" />
        </router-view>
      </v-container>
    </v-main>
  </v-expand-transition>
</template>