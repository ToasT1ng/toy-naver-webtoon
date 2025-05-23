import {useQuery} from "@tanstack/vue-query";
import {computed, ref} from "vue";
import {getEveryWebtoon, getWebtoon} from "@/api/webtoon";

export const useWebtoons = () => {
    const query = useQuery({
        queryKey: computed(() => ['webtoons']),
        queryFn: async () => {
            const res = await getEveryWebtoon()
            return res.result
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
            return await getWebtoon({id: webtoonId.value})
        },
        enabled: true,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        ...query,
    }
}