import {useQuery} from "@tanstack/vue-query";
import {computed, ref} from "vue";
import {getEveryWebtoon, getWebtoon} from "@/api/webtoon";
import {extractValidData} from "@/utils/extractValidData";

export const useWebtoons = () => {
    const query = useQuery({
        queryKey: computed(() => ['webtoons']),
        queryFn: async () => {
            const result = await getEveryWebtoon()
            return extractValidData(result)
        },
        enabled: true,
        staleTime: 1000 * 10,
    })

    return {
        ...query,
    }
}

export const useWebtoon = () => {
    const webtoonId = ref<number | undefined>(undefined)
    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }
    const query = useQuery({
        queryKey: computed(() => ['webtoon', webtoonId.value]),
        queryFn: async () => {
            if (!webtoonId.value) {
                return Promise.reject(new Error('webtoonId is required'))
            }
            const result = await getWebtoon({id: webtoonId.value})
            return extractValidData(result)
        },
        enabled: true,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        ...query,
    }
}