<script setup lang="ts">
import {useDailyWebtoon} from "@/composables/useDailyWebtoon";

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
        <v-card class="card-item">
          <v-card-item class="pa-0">
            <v-img
                height="100%"
                cover
                src="https://image-comic.pstatic.net/webtoon/783053/thumbnail/thumbnail_IMAG21_d7732f14-26da-4e35-8762-660cc87b53f1.jpg"
            />
          </v-card-item>
          <v-card-text class="pa-1 mt-1 pb-0 text-subtitle-1 font-weight-bold ellipsis">
            {{ item.title }}
          </v-card-text>
          <v-card-text class="pa-1 pt-0 pb-0 text-body-2 font-weight-medium ellipsis">
            {{ item.authorNames }}
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
  flex: 0 0 calc(20% - 8px);
  max-width: calc(20% - 8px);
  padding: 0;
}

.ellipsis {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>