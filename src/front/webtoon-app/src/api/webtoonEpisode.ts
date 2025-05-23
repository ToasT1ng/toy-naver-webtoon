import apiClient from './index'
import {AxiosResponse} from "axios";

interface IPagingWebtoonEpisodesRequest {
    webtoonId: number
    pageNo: number
    pageSize: number
}

interface IEpisode {
    episodeId: number;
    title: string;
    thumbnail: string;
    rating: string;
    uploadDate: string;
}

interface IPagingWebtoonEpisodesResponse {
    webtoonId: number;
    pageNo: number;
    pageSize: number;
    totalCount: number;
    totalPage: number;
    isLastPage: boolean;
    content: IEpisode[];
}

export const getPagingWebtoonEpisodes = async (
    req: IPagingWebtoonEpisodesRequest
): Promise<IPagingWebtoonEpisodesResponse> => {
    const response: AxiosResponse<IPagingWebtoonEpisodesResponse> = await apiClient.get<IPagingWebtoonEpisodesResponse>(`/webtoons/${req.webtoonId}/episodes`, {
        params: {
            pageNo: req.pageNo,
            pageSize: req.pageSize,
        },
    })
    return response.data
}