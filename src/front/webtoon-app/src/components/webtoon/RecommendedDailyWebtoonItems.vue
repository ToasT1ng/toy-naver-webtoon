<script setup lang="ts">
import {useDailyRecommendedWebtoon} from "@/composables/useDailyWebtoon";
import {navigateToWebtoonDetail} from "@/utils/navigation";
import {computed} from "vue";
const {data} = useDailyRecommendedWebtoon()
const items = computed(() => data.value?.result)
</script>

<template>
  <v-row
      class="flex-wrap pa-0 ma-0"
      style="gap: 10px;"
  >
    <template v-if="Array.isArray(items) && items.length">
      <v-col
          v-for="item in items"
          :key="item.id"
          cols="auto"
          class="pa-0 webtoon-col mb-1"
      >
        <v-card class="card-item" elevation="0">
          <v-card-item class="pa-0">
            <v-img
                class="with-border"
                style="height: 240px"
                cover
                :src="item.thumbnail"
                @click="navigateToWebtoonDetail(item.id, 'mon')"
            />
          </v-card-item>
          <v-card-text class="pa-1 mt-1 pb-0 text-subtitle-1 font-weight-extra-bold ellipsis">
            {{ item.title }}
          </v-card-text>
          <v-card-text class="pa-1 pt-0 pb-0 text-body-2 font-weight-semi-bold ellipsis">
            {{ item.authorNames }}
          </v-card-text>
          <v-card-text class="pa-1 pt-0 pb-0 text-body-2 font-weight-semi-bold ellipsis">
            {{ item.latestEpisodeName }}
          </v-card-text>
          <v-card-text class="pa-1 pt-0 text-caption font-weight-regular">
            {{ item.rating }}
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

.with-border {
  border: 1px solid #ccc;
  border-radius: 8px;
}
</style>