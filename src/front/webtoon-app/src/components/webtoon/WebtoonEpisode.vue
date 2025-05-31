<script setup lang="ts">
import {onMounted, ref} from "vue";
import {navigateToWebtoonDetail, navigateToWebtoonEpisode} from "@/utils/navigation";
import {useWebtoonStore} from "@/stores/webtoonStore";
import {useWebtoonEpisode, useWebtoonEpisodeNavigation} from "@/composables/useWebtoonEpisodes";

interface Props {
  webtoonId: string;
  episodeId: string;
}

const props = defineProps<Props>();
const webtoonStore = useWebtoonStore();

const {
  data: webtoonEpisodeNavigationData,
  updateWebtoonId: webtoonEpisodeNavigationUpdateWebtoonId,
  updateEpisodeId: webtoonEpisodeNavigationUpdateEpisodeId
} = useWebtoonEpisodeNavigation()

const {
  data: webtoonEpisodeData,
  updateWebtoonId: webtoonEpisodeUpdateWebtoonId,
  updateEpisodeId: webtoonEpisodeUpdateEpisodeId
} = useWebtoonEpisode()

onMounted(() => {
  console.log("episodeId:", props.episodeId);
  console.log("tab:", webtoonStore.tab);
  webtoonEpisodeUpdateWebtoonId(Number(props.webtoonId));
  webtoonEpisodeUpdateEpisodeId(Number(props.episodeId));
  webtoonEpisodeNavigationUpdateWebtoonId(Number(props.webtoonId));
  webtoonEpisodeNavigationUpdateEpisodeId(Number(props.episodeId));
});

function onClickNavigateToEpisode(episodeId: number) {
  if (episodeId !== 0) {
    navigateToWebtoonEpisode(Number(props.webtoonId), episodeId);
  } else {
    console.log("없습니다.");
  }
}
</script>

<template>
  <v-card class="pa-0 rounded-0" elevation="0">
    <v-row class="episode-header no-gutters justify-space-between">
      <v-col class="title-area d-flex align-center ml-10" style="height: 50px; overflow: visible;">
        <span class="title-text mr-4"><a>〈</a></span>
        <span class="title-text with-divider">{{ webtoonEpisodeData?.webtoonTitle }}</span>
        <span class="title-text">{{ webtoonEpisodeData?.title }}</span>
      </v-col>
      <v-col class="tool-area d-flex justify-end align-center mr-10" style="height: 50px;" cols="auto">
        <div class="button-group">
          <v-btn class="nav-button" @click="onClickNavigateToEpisode(webtoonEpisodeNavigationData?.previousEpisodeId)">
            <v-icon>mdi-menu-left</v-icon>
            이전화
          </v-btn>
          <v-btn class="nav-button" @click="navigateToWebtoonDetail(Number(props.webtoonId), webtoonStore.tab)">
            <v-icon>mdi-format-list-bulleted</v-icon>
            목록
          </v-btn>
          <v-btn class="nav-button" @click="onClickNavigateToEpisode(webtoonEpisodeNavigationData?.nextEpisodeId);">다음화
            <v-icon>mdi-menu-right</v-icon>
          </v-btn>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <div v-if="webtoonEpisodeData?.images?.length">
          <div
            v-for="(img, index) in webtoonEpisodeData.images"
            :key="index"
            class="image-wrapper"
          >
            <v-img
              :src="img"
              class="mb-4"
              height="100%"
              cover
            />
          </div>
        </div>
        <div v-else>
          <p>이미지가 없습니다.</p>
        </div>
      </v-col>
    </v-row>
  </v-card>
</template>

<style scoped>
.episode-header {
  background-color: black;
  padding: 12px;
  position: relative;
}

.title-area {
  overflow: visible;
}

.title-text {
  color: white;
  font-weight: bold;
  font-size: 16px;
  line-height: 40px;
  position: relative;
}

.title-text.with-divider {
  margin-right: 20px;
}

::v-deep(.with-divider::after) {
  content: "";
  position: absolute;
  right: -10px;
  top: 53%;
  transform: translateY(-50%);
  height: 13px;
  width: 1px;
  background-color: #e0e0e0;
  opacity: 0.3;
}

.button-group {
  display: flex;
  gap: 20px;
  position: relative;
}

.nav-button {
  color: white;
  font-weight: bold;
  background-color: black;
  font-size: 16px;
  height: 40px;
  line-height: 40px;
  padding: 0 8px;
  position: relative;
}

::v-deep(.nav-button + .nav-button::before) {
  content: "";
  position: absolute;
  left: -10px;
  top: 53%;
  transform: translateY(-50%);
  height: 12px;
  width: 1px;
  background-color: #e0e0e0;
  opacity: 0.3;
}
</style>
<style scoped>
.image-wrapper {
  margin-left: 25%;
  margin-right: 25%;
}

.image-wrapper .v-img {
  width: 100%;
  max-width: 100%;
  object-fit: contain;
}
</style>