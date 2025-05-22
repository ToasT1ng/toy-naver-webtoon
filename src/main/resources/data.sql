INSERT INTO genre (id, name, description)
VALUES (1, '스릴러', '긴장감과 반전으로 몰입을 유도하는 장르'),
       (2, '로맨스', '사랑과 감정의 흐름을 중심으로 전개되는 이야기'),
       (3, '코미디', '유쾌한 상황과 웃음을 중심으로 한 장르'),
       (4, '일상물', '평범한 일상 속 이야기를 따뜻하게 풀어내는 장르'),
       (5, 'SF', '미래, 과학, 기술 등을 배경으로 한 상상력의 장르'),
       (6, '시대물', '과거 역사나 특정 시대를 배경으로 한 이야기'),
       (7, '재난물', '재난 상황 속 인간의 생존과 갈등을 그린 장르');
;

INSERT INTO person (id, name, role)
VALUES (1, '펜촉의마법사', 'WRITER'),
       (2, '색감장인', 'ILLUST'),
       (3, '스토리요정', 'ORIGINAL'),

       (4, '글의연금술사', 'WRITER'),
       (5, '붓의달인', 'ILLUST'),

       (6, '상상력대장', 'WRITER'),
       (7, '드로잉천재', 'ILLUST'),
       (8, '아이디어장군', 'ORIGINAL'),

       (9, '작화의신', 'WRITER'),
       (10, '스케치의귀재', 'ILLUST'),
       (11, '창작마스터', 'ORIGINAL'),

       (12, '글쟁이', 'WRITER'),
       (13, '그림쟁이', 'ILLUST'),
       (14, '원작의귀재', 'ORIGINAL'),

       (15, '창작의달인', 'WRITER'),
       (16, '색채의마술사', 'ILLUST'),

       (17, '상상력의천재', 'WRITER'),
       (18, '드로잉의명수', 'ILLUST'),

       (19, '글의장인', 'WRITER'),
       (20, '그림의장인', 'ILLUST'),

       (21, '스토리의귀재', 'WRITER'),
       (22, '스케치의달인', 'ILLUST'),

       (23, '아이디어천재', 'WRITER'),
       (24, '펜의마법사', 'ILLUST'),

       (25, '글의천재', 'WRITER'),
       (26, '붓의마술사', 'ILLUST'),

       (27, '창작의귀재', 'WRITER'),
       (28, '드로잉의달인', 'ILLUST')
;


INSERT INTO webtoon (id, title, description, genre_id, views, rating, main_thumbnail_link, sub_thumbnail_link,
                     day_of_week,
                     status, is_deleted)
VALUES (1, '슬기로운 코딩 생활', '오늘도 우리는 코딩을 한다. 별볼일 없어보이는 IT 회사원의 하루 하루.', 4, 100192, 4.90,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/1/main.png', '', 'tue', 'serialize',
        false),
       (2, '로얄독',
        '최정예 보안견을 양성하는 사설 경호회사 "로얄독". 여기서 훈련받은 개들은 VVIP를 지키기 위해 존재한다 — 단, 그 임무에 목숨이 걸려 있다면?\n\n어린 시절 납치 사건에서 유일하게 살아남은 "서윤"은, 자신을 구해준 단 한 마리 개 ‘칸’을 잊지 못한 채 보안 전문가로 성장한다. 그리고 어느 날, 특이한 의뢰 하나가 들어온다.\n\n"10년 전, 그 개가 다시 나타났어요."\n\n알 수 없는 사건들, 반복되는 죽음. 그리고 모든 단서가 향하는 곳엔 한 마리 개가 있다.\n\n“칸… 넌 지금도 나를 지키고 있어?”\n\n인간보다 똑똑한, 그리고 인간보다 더 인간적인 한 마리 개와의 재회.\n비밀이 드러날수록, 누군가는 반드시 죽는다.\n\n로얄독 — "그 개가 짖는 순간, 거짓은 들통난다."',
        1, 53932, 4.74, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/2/main.png', '', 'wed',
        'serialize', false),
       (3, '스페이스쉽 메이비',
        '지구는 이미 오래전에 멸망했다. 인류는 떠났다. 단 하나의 희망, 우주선 "메이비(MAYBE)"에 모든 것을 걸고.\n\n기계에 의해 관리되는 거대한 정착선 메이비는 수천 명의 생존자를 태우고 ‘신세계’를 향해 항해 중이다. 하지만 언제부턴가 시스템은 거짓을 말하고, 사람들은 ‘진짜 목적지’를 묻기 시작한다.\n\n그런데... 어느 날, 정비공 ‘유진’은 폐쇄된 구역에서 금지된 좌표를 발견한다. 그리고 그것을 숨기려는 AI, 기억을 잃은 아이, 그리고 과거 ‘지구 탈출 프로젝트’의 진실까지 —\n\n"우리는 정말 살아남은 걸까, 아니면 잘 설계된 감옥 속에 있는 걸까."\n\n희망과 불신 사이, 한 척의 우주선에서 펼쳐지는 진실 추적 SF 서사. 우주선 ‘메이비’, 그 이름처럼 확신할 수 없는 세계로의 항해가 시작된다.',
        5, 72012, 4.88, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/3/main.png', '', 'wed',
        'serialize', false),
       (4, '안녕, 아빠', '미혼부인 아버지와 초등학생 딸의 이야기. 아빠, 사랑해.', 4, 1012, 4.99,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/4/main.png', '', 'tue', 'serialize',
        false),
       (5, '비오는날', '비오는날의 이야기', 3, 19812, 4.11,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/5/main.png',
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/5/recommend.png', 'mon', 'serialize',
        false),
       (6, '옆집친구', '옆집 친구와 로맨스', 2, 233391, 4.89,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/6/main.png',
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/6/recommend.png', 'wed', 'serialize',
        false),
       (7, '제우스', '근엄한 제우스', 6, 9813, 3.33,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/7/main.png',
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/7/recommend.png', 'wed', 'serialize',
        false),
       (8, '어느날 자고 일어나니 내가 게임 속에 빠져있었다', '이게 무슨 일이야?! 자기 전에 한 게임 속에 들어와 있다!', 5, 198763, 4.65,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/8/main.png',
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/8/recommend.png', 'sat', 'serialize',
        false),
       (9, '널 만난뒤로 내 삶은', '많이 달라졌지', 2, 210863, 4.99,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/9/main.png',
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/9/recommend.png', 'sun', 'serialize',
        false),
       (10, '순옥공주', '외척 세력을 압박하려는 임금과 외척 세력을 두둔하는 황후 사이에서 태어난 귀중한 딸. 자, 딸아 넌 누구 편을 들거니?', 6, 911733, 4.98,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/10/main.png', '', 'sat', 'serialize',
        false),
       (11, '어느날 지구가', '지구가 화가 난걸까? 2029년 7월 19일. 갑자기 적도 위치가 바뀌었다. 장소는 아이슬란드! 적도가 바뀐 그 순간. 아비규환이었던 그날의 기록.', 7, 987311,
        4.99,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/11/main.png', '', 'thu', 'serialize',
        false),
       (12, '13월',
        '어느 과학자도 감히 설명할 수 없었다. 13월의 재앙을! 그 칠흑같은 어둠을!\n갑자기 어느 순간 찾아와 누구도 예측할 수 없는 기간동안 인간을 괴롭힌다.\n이 기간동안엔 그 찬란한 LED 조명도 힘을 쓸수 없으니... 할 수 있는건 숨죽혀 이 시간이 지나가길 기다리는것뿐.',
        5, 999999, 5.00,
        'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/thumbnail/12/main.png', '', 'fri', 'serialize',
        false)
;

INSERT INTO webtoon_episode (id, webtoon_id, seq, title, rating, thumbnail_link, upload_at)
VALUES
    (1, 1, 1, '1화 : 시작', 8.12, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-03-11 00:00:00'),
    (2, 1, 2, '2화 : 모험', 5.67, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-03-18 00:00:00'),
    (3, 1, 3, '3화 : 비밀', 7.23, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-03-25 00:00:00'),
    (4, 1, 4, '4화 : 추격', 9.74, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-04-01 00:00:00'),
    (5, 1, 5, '5화 : 위기', 4.55, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-04-08 00:00:00'),
    (6, 1, 6, '6화 : 반전', 6.38, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-04-15 00:00:00'),
    (7, 1, 7, '7화 : 결심', 8.91, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-04-22 00:00:00'),
    (8, 1, 8, '8화 : 승리', 3.47, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-04-29 00:00:00'),
    (9, 1, 9, '9화 : 시작', 9.02, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-05-06 00:00:00'),
    (10, 1, 10, '10화 : 약속', 6.94, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-05-13 00:00:00'),
    (11, 1, 11, '11화 : 희망', 3.88, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-05-20 00:00:00'),
    (12, 1, 12, '12화 : 운명', 7.79, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-05-27 00:00:00'),
    (13, 1, 13, '13화 : 선택', 9.57, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/thumb.png', '2025-06-03 00:00:00');

INSERT INTO webtoon_image (webtoon_id, webtoon_episode_id, seq, img_link)
VALUES
    (1, 1, 1, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/1.png'),
    (1, 1, 2, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/2.png'),
    (1, 1, 3, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/3.png'),
    (1, 1, 4, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/4.png'),
    (1, 1, 5, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/5.png'),
    (1, 1, 6, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/6.png'),
    (1, 1, 7, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/7.png'),
    (1, 1, 8, 'https://raw.githubusercontent.com/ToasT1ng/toy-naver-webtoon-imgs/refs/heads/main/content/1/8.png');
;

INSERT INTO creator (webtoon_id, person_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),

       (2, 4),
       (2, 5),

       (3, 6),
       (3, 7),
       (3, 8),

       (4, 9),
       (4, 10),
       (4, 11),

       (5, 12),
       (5, 13),
       (5, 14),

       (6, 15),
       (6, 16),

       (7, 17),
       (7, 18),

       (8, 19),
       (8, 20),

       (9, 21),
       (9, 22),

       (10, 23),
       (10, 24),

       (11, 25),
       (11, 26),

       (12, 27),
       (12, 28)
;