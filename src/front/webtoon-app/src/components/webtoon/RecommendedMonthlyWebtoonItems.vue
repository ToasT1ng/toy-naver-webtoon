<script setup lang="ts">
import {useMonthlyRecommendedWebtoon} from "@/composables/useMonthlyWebtoon";
import {navigateToWebtoonDetail} from "@/utils/navigation";
const {data} = useMonthlyRecommendedWebtoon()
</script>

<template>
  <v-row
      class="flex-wrap pa-0 ma-0"
      style="gap: 10px;"
  >
    <template v-if="Array.isArray(data) && data.length">
      <v-col
          v-for="item in data"
          :key="item.id"
          cols="auto"
          class="pa-0 webtoon-col mb-1"
      >
        <v-card class="card-item" elevation="0">
          <v-card-item class="pa-0">
            <v-img
                class="with-border"
                height="100%"
                cover
                :src="item.thumbnail"
                @click="navigateToWebtoonDetail(item.id, 'mon')"
            />
          </v-card-item>
          <v-card-text class="pa-1 mt-1 pb-0 text-subtitle-1 font-weight-bold ellipsis">
            {{ item.title }}
          </v-card-text>
          <v-card-text class="pa-1 pt-0 pb-0 text-body-2 font-weight-medium ellipsis">
            {{ item.authorNames }}
          </v-card-text>
          <v-card-text class="pa-1 pt-0 pb-0 text-body-2 font-weight-medium ellipsis-two-lines">
            {{ item.description }}
          </v-card-text>
        </v-card>
      </v-col>
    </template>
    <template v-else>
      <v-col cols="12" class="pa-0">
        등록된 웹툰이 없습니다.
      </v-col>
    </template>
  </v-row>
</template>
<style scoped>
.webtoon-col {
  flex: 0 0 calc(33% - 8px);
  max-width: calc(33% - 8px);
  padding: 0;
}

.ellipsis {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.ellipsis-two-lines {
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  text-overflow: ellipsis;
}

.with-border {
  border: 1px solid #ccc;
  border-radius: 8px;
}
</style>