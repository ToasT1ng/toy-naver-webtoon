<script setup lang="ts">
const props = defineProps({
  daysOfWeek: {
    type: String,
    required: true
  }
})
</script>

<template>
  <v-container fluid class="pt-1 px-0">
    <v-row>
      <v-col cols="12">
        <v-card>
          <v-card-title>오늘 웹툰</v-card-title>
          <v-card-text>
            지금 요일 : {{ daysOfWeek }}
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>