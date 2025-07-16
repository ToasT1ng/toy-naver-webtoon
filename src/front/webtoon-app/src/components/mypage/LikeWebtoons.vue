<script setup lang="ts">
import {useWebtoonLikes} from "@/composables/useWebtoonLikes";
import {computed, ref} from "vue";
import {TDailyWebtoonsSortOption, TLikeWebtoonsSortOption} from "@/features/webtoon/types/sortOptions";

const {data: webtoonLikes} = useWebtoonLikes()

const totalCount = computed(() => webtoonLikes.value?.result.length)

const selectedSort = ref<TLikeWebtoonsSortOption>('UPLOADED')

const sortOptions = [
  {label: '업데이트순', value: 'UPLOADED'},
  {label: '최근등록순', value: 'LIKED_AT'},
] satisfies readonly { label: string; value: TLikeWebtoonsSortOption }[]

function onSort() {
  console.log("Sorting by:", selectedSort.value);
}

</script>

<template>
  <v-container fluid class="pt-1 px-0">
    <v-row>
      <v-col class="mt-2" cols="12">
        <v-card elevation="0">
          <v-card-title class="d-flex align-center">
            <div class="font-weight-bold me-2">관심웹툰</div>

            <div class="d-flex align-center ml-2 mt-2 text-body-2 font-weight-bold text-grey-darken-1">
              <span class="me-2">전체 {{ totalCount ?? 0 }}</span>
              <v-btn-toggle
                  v-model="selectedSort"
                  color="black"
                  variant="plain"
                  class="pa-0 ma-0"
                  style="height: 24px; line-height: 24px; min-height: 24px;"
                  mandatory
              >
                <v-btn
                    v-for="option in sortOptions"
                    :key="option.value"
                    :value="option.value"
                    @click="onSort"
                    small
                    elevation="0"
                    :ripple="false"
                    class="pa-0 mx-1"
                    style="min-width: auto;"
                >
                  {{ option.label }}
                </v-btn>
              </v-btn-toggle>
            </div>
          </v-card-title>
          <v-card-item>
            <v-list>
              <v-list-item
                  v-for="webtoon in webtoonLikes"
                  :key="webtoon.id"
                  class="pa-3"
              >
                <v-list-item-avatar size="80">
                  <v-img :src="webtoon.thumbnailUrl" cover></v-img>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title class="font-weight-bold">
                    {{ webtoon.title }}
                  </v-list-item-title>
                  <v-list-item-subtitle>
                    작가
                  </v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action class="text-caption text-grey">
                  {{ webtoon.likedAt }}
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-card-item>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>