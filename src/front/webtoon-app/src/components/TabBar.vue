<script setup lang="ts">
import {useRoute} from 'vue-router'

const route = useRoute()
</script>

<template>
  <v-app-bar app color="white" light elevation="0" class="tab-bar-container">
    <v-btn :to="{ name: 'WebtoonAll' }" :class="['text-subtitle-1', { active: route.path.startsWith('/webtoon') }]">
      웹툰
    </v-btn>
    <v-btn :to="{ name: 'mypage' }" :class="['text-subtitle-1', { active: route.name === 'mypage' }]">
      마이페이지
    </v-btn>
  </v-app-bar>
</template>

<style scoped>
.tab-bar-container {
  border: 1px solid #ccc;
}

.v-btn {
  border-radius: 0;
  height: 100%;
  font-weight: 700;
}

.v-btn.active {
  background-color: #f0f0f0;
}
</style>