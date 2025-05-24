import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useWebtoonStore = defineStore('webtoon', () => {
    const webtoonId = ref<number | null>(null)
    const tab = ref<string>('')

    function setWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }

    function setTab(newTab: string) {
        tab.value = newTab
    }

    return {
        webtoonId,
        tab,
        setWebtoonId,
        setTab
    }
})