<script setup lang="ts">
import { onMounted, ref } from "vue";
import { navigateToWebtoonDetail, navigateToWebtoonEpisode } from "@/utils/navigation";

interface Props {
  episodeId: string;
  tab: string;
}
const props = defineProps<Props>();

interface IEpisodeContentImage {
  url: string;
}

interface IWebtoonEpisode {
  webtoonId: number;
  webtoonTitle: string;
  episodeId: number;
  episodeTitle: string;
  previousEpisodeId: number;
  nextEpisodeId?: number;
  likeCount: number;
  images: IEpisodeContentImage[];
}

const data = ref<IWebtoonEpisode>({
  webtoonId: 123456,
  webtoonTitle: "웹툰제목",
  episodeId: 123,
  episodeTitle: "1화 시작",
  previousEpisodeId: 122,
  nextEpisodeId: 124,
  likeCount: 1000,
  images: [
    {
      url: "https://image-comic.pstatic.net/webtoon/796075/128/thumbnail.jpg",
    },
    {
      url: "https://image-comic.pstatic.net/webtoon/796075/128/thumbnail.jpg",
    },
  ],
});

onMounted(() => {
  console.log("episodeId:", props.episodeId);
  console.log("tab:", props.tab);
});

function onClickNextEpisode() {
  if (data.value.nextEpisodeId !== undefined) {
    navigateToWebtoonEpisode(data.value.nextEpisodeId, props.tab);
  }
}
</script>

<template>
  <v-card class="pa-0 rounded-0" elevation="0">
    <v-row class="episode-header no-gutters justify-space-between">
      <v-col class="title-area d-flex align-center ml-10" style="height: 50px; overflow: visible;">
        <span class="title-text mr-4"><a>〈</a></span>
        <span class="title-text with-divider">{{ data.webtoonTitle }}</span>
        <span class="title-text">{{ data.episodeTitle }}</span>
      </v-col>
      <v-col class="tool-area d-flex justify-end align-center mr-10" style="height: 50px;" cols="auto">
        <div class="button-group">
          <v-btn class="nav-button" @click="navigateToWebtoonEpisode(data.previousEpisodeId, props.tab)"><v-icon>mdi-menu-left</v-icon>이전화</v-btn>
          <v-btn class="nav-button" @click="navigateToWebtoonDetail(data.webtoonId, props.tab)"><v-icon>mdi-format-list-bulleted</v-icon>목록</v-btn>
          <v-btn class="nav-button" @click="onClickNextEpisode();">다음화<v-icon>mdi-menu-right</v-icon></v-btn>
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