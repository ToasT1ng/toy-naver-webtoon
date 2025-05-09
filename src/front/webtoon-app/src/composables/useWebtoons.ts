import {useQuery} from "@tanstack/vue-query";
import {computed} from "vue";
import {getEveryWebtoon} from "@/api/webtoon";

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