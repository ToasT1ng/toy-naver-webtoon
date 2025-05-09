<script setup lang="ts">

const props = defineProps({
  webtoonId: {
    type: String,
    required: true
  },
  tab: {
    type: String,
    required: true
  }
})

</script>

<template>
  <v-card>
    <v-card-title class="font-weight-bold">웹툰 상세</v-card-title>
    <v-card-text>
      웹툰 상세 컴포넌트
    </v-card-text>
    <v-card-text>
      {{ props.webtoonId }}
    </v-card-text>
  </v-card>
</template>

<style scoped>

</style>