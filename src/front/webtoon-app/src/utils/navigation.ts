import router from "@/router";

export const navigateToWebtoonDetail = (webtoonId: number, tab: string) => {
    router.push({
        path: `/webtoon/detail/${webtoonId}`,
        query: {tab}
    }).then(r => {
        // console.log(r)
    })
 }

export const navigateToWebtoonEpisode = (webtoonId: number, episodeId: number)=> {
    router.push({
        path: `/webtoon/${webtoonId}/episode/${episodeId}`,
    }).then(r => {
        // console.log(r)
    })
}