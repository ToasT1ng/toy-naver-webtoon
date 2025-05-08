<script setup lang="ts">
import RecommendedMonthlyWebtoonItems from "@/components/webtoon/RecommendedMonthlyWebtoonItems.vue";
</script>

<template>
  <v-container fluid class="pt-1 px-0">
    <v-row>
      <v-col class="mt-2" cols="12">
        <v-card elevation="0">
          <v-card-title class="font-weight-bold">이달의 신규 웹툰</v-card-title>
          <v-card-item>
            <RecommendedMonthlyWebtoonItems/>
          </v-card-item>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>