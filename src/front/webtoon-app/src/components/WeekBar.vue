<script lang="ts">
export default {
  name: 'WeekBar',
}
</script>

<template>
  <v-app-bar app color="white" light elevation="0" class="week-bar" density="comfortable">
    <v-btn>요일전체</v-btn>
    <v-btn>월</v-btn>
    <v-btn>화</v-btn>
    <v-btn>수</v-btn>
    <v-btn>목</v-btn>
    <v-btn>금</v-btn>
    <v-btn>토</v-btn>
    <v-btn>일</v-btn>
  </v-app-bar>
</template>

<style scoped>
.week-bar {
  border: 1px solid #ccc;
}
.v-btn {
  border-radius: 0;
  height: 100%;
}
</style>