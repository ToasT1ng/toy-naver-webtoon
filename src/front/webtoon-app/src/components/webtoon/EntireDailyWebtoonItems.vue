<script setup lang="ts">
import {useDailyWebtoon} from "@/composables/useDailyWebtoon";

import {navigateToWebtoonDetail} from "@/utils/navigation";

const {data} = useDailyWebtoon()

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
                @click="navigateToWebtoonDetail(item.id, 'tab을 넣어야합니다.....')"
            />
          </v-card-item>
          <v-card-text class="pa-1 mt-1 pb-0 text-subtitle-1 font-weight-extra-bold ellipsis">
            {{ item.title }}
          </v-card-text>
          <v-card-text class="pa-1 pt-0 pb-0 text-body-2 font-weight-semi-bold ellipsis">
            {{ item.authorNames }}
          </v-card-text>
          <v-card-text class="pa-1 pt-0 text-body-3 font-weight-semi-bold text-grey">
            <span class="star-with-rating">
              <svg
                  class="star-icon"
                  width="14"
                  height="14"
                  viewBox="0 0 24 24"
                  fill="#9e9e9e"
                  xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M12 17.27L18.18 21l-1.64-7.03L22 9.24l-7.19-.61L12 2
                     9.19 8.63 2 9.24l5.46 4.73L5.82 21z"/>
              </svg>{{ item.rating }}
            </span>
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
  flex: 0 0 calc(20% - 8px);
  max-width: calc(20% - 8px);
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

.star-with-rating {
  display: inline-flex;
  align-items: center;
}

.star-icon {
  position: relative;
  top: 1px;
  margin-right: 2px;
  flex-shrink: 0;
}
</style>