<%@ page import="java.util.List" %>
<%@ page import="bean.Teams" %>
<%@ page import="service.AggregateService" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="bean.BangxepHangAggregate" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=vi>
<head>
    <title>Bảng xếp hạng bóng đá </title>
    <meta name=description content="Bảng xếp hạng bóng đá Anh - Bảng xếp hạng Ngoại hạng Anh, hạng nhất Anh, League Cup, Cúp FA, theo dõi BXH bóng đá Anh sau lượt trận mới diễn ra">
    <meta name=keywords content="bang xep hang bong da anh,bang xep hang ngoai hang anh,bxh ngoai hang anh,BXH NHA,BXH bong da anh">
    <meta name=news_keywords content="bang xep hang bong da anh, bang xep hang ngoai hang anh, bxh ngoai hang anh, BXH NHA, BXH bong da anh">
    <link rel=canonical href=https://bongda24h.vn/bong-da-anh/bang-xep-hang-1.html>
    <link rel=amphtml href=https://bongda24h.vn/bong-da-anh/bang-xep-hang-1-amp.html>
    <meta charset=utf-8>
    <link rel=dns-prefetch href=https://static.bongda24h.vn>
    <meta name=theme-color content=#069241>
    <meta name=author content=Bongda24h>
    <meta name=robots content=index,follow,noydir,noodp>
    <meta name=Googlebot-News content=index,follow>
    <meta name=googlebot content=index,follow>
    <meta name=viewport content="width=device-width, initial-scale=1.0, maximum-scale=5.0, user-scalable=yes">
    <meta name=geo.region content=VN-HN>
    <meta name=geo.position content=21.031227;105.782473>
    <meta name=ICBM content="21.031227, 105.782473">
    <meta property=fb:pages content=717309238310793>
    <meta property=fb:pages content=919932411414742>
    <meta name=_mg-domain-verification content=04756de6e40abfd8c7662ee2d8160861>
    <meta name=dmca-site-verification content=Qm4zT3pBU1p4T1gwSWhjcFlkZ0Radz090>
    <meta name=ahrefs-site-verification content=09fa5977e3d794900a13b4b2b39678c22a0d4d6b581fde8c94a1511aa2289f30>
    <link rel=manifest href=/manifest.json>
    <link rel=apple-touch-icon sizes=57x57 href=https://cdn.bongda24h.vn/images/icons/57.png>
    <link rel=apple-touch-icon sizes=72x72 href=https://cdn.bongda24h.vn/images/icons/72.png>
    <link rel=apple-touch-icon sizes=114x114 href=https://cdn.bongda24h.vn/images/icons/114.png>
    <link rel=apple-touch-icon sizes=144x144 href=https://cdn.bongda24h.vn/images/icons/144.png>
    <link rel=icon type=image/png sizes=192x192 href=https://cdn.bongda24h.vn/images/icons/192.png>
    <link rel=icon type=image/png sizes=32x32 href=https://cdn.bongda24h.vn/images/icons/32.png>
    <link rel=icon type=image/png sizes=96x96 href=https://cdn.bongda24h.vn/images/icons/96.png>
    <link rel=icon type=image/png sizes=16x16 href=https://cdn.bongda24h.vn/images/icons/16.png>
    <link rel="stylesheet" href="styles.css">
    <script>
        !function(e, t, a, n, g) {
            e[n] = e[n] || [],
                e[n].push({
                    "gtm.start": (new Date).getTime(),
                    event: "gtm.js"
                });
            var m = t.getElementsByTagName(a)[0]
                , r = t.createElement(a);
            r.async = !0,
                r.src = "https://www.googletagmanager.com/gtm.js?id=GTM-MQRPJV",
                m.parentNode.insertBefore(r, m)
        }(window, document, "script", "dataLayer");
    </script>
    <script async src="https://www.google.com/cse/cse.js?cx=partner-pub-6162392498535478:1316534544"></script>
    <script type=application/ld+json>
        {"@context":"http://schema.org","@type":"BreadcrumbList","itemListElement":[{"@type":"ListItem","position":1,"item":{"@id":"https://bongda24h.vn/","name":"Bóng đá 24h"}},{"@type":"ListItem","position":"2","name":"Bảng xếp hạng","item":"https://bongda24h.vn/bang-xep-hang.html"},{"@type":"ListItem","position":"3","name":"Bảng xếp hạng ngoại hạng Anh hôm nay","item":"https://bongda24h.vn/bong-da-anh/bang-xep-hang-1.html"}]}
    </script>

    <link rel=preload href=https://cdn.bongda24h.vn/webfonts/fa-light-300.woff2 as=font type=font/woff2 crossorigin>
    <link rel=preload href=https://cdn.bongda24h.vn/webfonts/fa-brands-400.woff2 as=font type=font/woff2 crossorigin>

    <script data-ad-client=ca-pub-6162392498535478 async src=https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js></script>
    <script>
        var arfAsync = arfAsync || [];
    </script>
    <script id=arf-core-js onerror="window.arferrorload=true" src=//media1.admicro.vn/cms/Arf.min.js async></script>
</head>
<body>
<noscript>
    <iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KVBRVN" height=0 width=0 style=display:none;visibility:hidden></iframe>
</noscript>
<%LocalDate date= AggregateService.getDate();%>
<header class=header>
    <div class=main-content>
                <span class=btn-menu data-pushbar-target=left>
                    <i class="fas fa-bars"></i>
                </span>
        <a href="/" class=header-logo>
            <img width=138 height=50 alt=logo-bongda24h src=https://cdn.bongda24h.vn/images/logo-bongda24h.svg>
        </a>
        <span class=header-time><%=date.getDayOfWeek()%>, <%=date%></span>
        <div class=header-right>

            <div class="header-item ggsearch">
                <gcse:search></gcse:search>
            </div>
        </div>
        <!-- <a class=globe24h href=/tin-the-thao-moi-nhat.html>Mới nhất</a> -->
    </div>
</header>

<main class=main>
    <div class=main-content>

        <div class=pad40>

            <header class=section-header>
                <h1 class=title-club-page><%= AggregateService.getDataL().get(0).getTen_giai_dau()%></h1>
            </header>
            <section class=section>
                <aside class=sidebar-left>
                    <div class=sidebar>
                        <div class=name-gx>Giải đấu</div>
                        <ul class=ul-sbar>
                            <li>
                                <a  href=index.jsp>Premier League</a>
                            </li>
                            <li>
                                <a href=v_league.jsp>V-League</a>
                            </li>

                            <li>
                                <a class=active href=la_liga.jsp>La Liga</a>
                            </li>

                        </ul>
                    </div>

                </aside>
                <div class=content-right>
                    <section class=section>
                        <div class=row-head>

                            <!-- <h2 id=titleLeague class="title-giaidau flet">BXH Ngoại hạng Anh (Mùa 2023/2024)</h2> -->
                        </div>
                        <div class=section-content id=AjaxContent>

                            <div class=box-bxh>
                                <div class=form-group>

                                    <div class=item-fright>

                                        <a title="Ngày cập nhật" href="">Ngày cập nhật: <%=date%> </a>
                                        <%--                                          <a title="Ngày cập nhật" href="">Ngày cập nhật: </a>--%>
                                    </div>
                                </div>

                                <div class=huongdan>Th: thắng | H: hòa | B: bại | HS: hiệu s | Đ: điểm</div>
                                <div class="table-scrol-x calc">
                                    <table class=table-bxh>
                                        <tbody>
                                        <tr>
                                            <th class=w-stt>TT</th>
                                            <th class=w-ten-clb>Đội</th>
                                            <th class=th-data data-name=Trận>
                                                <span class=hi_m>Trận</span>
                                            </th>
                                            <th class=th-data data-name=Th>
                                                <span class=hi_m>Thắng</span>
                                            </th>
                                            <th class=th-data data-name=H>
                                                <span class=hi_m>Hòa</span>
                                            </th>
                                            <th class=th-data data-name=B>
                                                <span class=hi_m>Bại</span>
                                            </th>
                                            <th class=th-data data-name=HS>
                                                <span class=hi_m>HS</span>
                                            </th>
                                            <th class=th-data data-name=Đ>
                                                <span class=hi_m>Điểm</span>
                                            </th>
                                            <th>5 trận gần nhất</th>
                                        </tr>
                                        <% List<BangxepHangAggregate> teamsList = AggregateService.getDataL();
                                            for ( BangxepHangAggregate t: teamsList) {
                                        %>

                                        <tr>
                                            <td>
                                                <%
                                                    List<Integer> ranks = Collections.singletonList(t.getHang()); // Assuming getHang() returns a List<Integer>

                                                    for (Integer rank : ranks) {
                                                        if (rank == null) {
                                                            continue; // Skip null ranks or handle them appropriately
                                                        }

                                                        if (rank <=4) {
                                                %>
                                                <span title="Dự vòng bảng Champions League" class="ranknum rank<%= rank %>"><%= rank %></span>
                                                <%
                                                } else if ( rank <6) {
                                                %>
                                                <span title="Dự vòng bảng Europa League" class="ranknum rank<%= rank %>"><%= rank %></span>
                                                <%
                                                } else if (rank >=6 && rank<=17) {
                                                %>
                                                <span><%= rank %></span>
                                                <%
                                                } else if (rank >= 18 ) {
                                                %>
                                                <span title="Xuống hạng Championship" class="ranknum rank<%= rank %>"><%= rank %></span>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </td>
                                            <td>
                                                <a class=link-clb href=/clubs/tottenham-5.html>
                                                    <%--                                                    <img class=bxhclb-icon alt=Tottenham src=https://static.bongda24h.vn/Medias/icon/2020/7/23/Totenham.png>Tottenham--%>
                                                    <img class=bxhclb-icon alt=Tottenham src=<%=t.getLogo()%>><%=t.getTen_doi_bong()%>
                                                </a>
                                            </td>
                                            <td><%=t.getSo_tran()%></td>
                                            <td><%=t.getTran_thang()%></td>
                                            <td><%=t.getTran_hoa()%></td>
                                            <td><%=t.getTran_thua()%></td>
                                            <td><%=t.getHe_so()%></td>
                                            <td><%=t.getDiem()%></td>
                                            <td>
                                                <% for (String i: t.getNam_tran_gan_nhat().split("")){
                                                    if(i.equals("H")) {
                                                %>

                                                <span class="bg-color bgyelow" title=Hòa>H</span>
                                                <%}
                                                    if(i.equals("T")){%>
                                                <span class="bg-color bggreen" title=Thắng>T</span>
                                                <%} if(i.equals("B")){%>
                                                <span class="bg-color bgred" title=Thắng>B</span>
                                                <%}%>
                                                <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                                <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                                <%}%>
                                            </td>
                                        </tr>
                                        <%}%>

                                        <%--                                        <tr>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span title="Dự vòng bảng Champions League" class="ranknum rank2">2</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/arsenal-4.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Arsenal src=https://static.bongda24h.vn/Medias/icon/2020/7/23/Arsenal.png>Arsenal--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>7</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>0</td>--%>
                                        <%--                                            <td>15</td>--%>
                                        <%--                                            <td>24</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span title="Dự vòng bảng Champions League" class="ranknum rank3">3</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/man-city-1471.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt="Man City" src=https://static.bongda24h.vn/Medias/icon/2017/6/26/manchester-city.jpg>Man City--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>8</td>--%>
                                        <%--                                            <td>0</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>15</td>--%>
                                        <%--                                            <td>24</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span title="Dự vòng bảng Champions League" class="ranknum rank4">4</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/liverpool-2.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Liverpool src=https://static.bongda24h.vn/Medias/icon/2020/7/23/Liverpool.png>Liverpool--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>7</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>14</td>--%>
                                        <%--                                            <td>23</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span title="Dự vòng bảng Europa League" class="ranknum rank5">5</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/aston-villa-8.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt="Aston Villa" src=https://static.bongda24h.vn/Medias/icon/2020/7/27/Aston-Villa_logo.png>Aston Villa--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>7</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>12</td>--%>
                                        <%--                                            <td>22</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>6</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/newcastle-united-1393.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Newcastle src=https://static.bongda24h.vn/Medias/icon/2020/7/27/Newcastle.jpg>Newcastle--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>5</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>15</td>--%>
                                        <%--                                            <td>17</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>7</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/brighton-2269.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Brighton src=https://static.bongda24h.vn/Medias/icon/2017/8/3/logo-brighton-hove-albion.jpg>Brighton--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>5</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>17</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>8</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/manchester-united-1.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt="Man United" src=https://static.bongda24h.vn/Medias/icon/2020/7/23/ManUnited.png>Man United--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>5</td>--%>
                                        <%--                                            <td>0</td>--%>
                                        <%--                                            <td>5</td>--%>
                                        <%--                                            <td>-5</td>--%>
                                        <%--                                            <td>15</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>9</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/west-ham-1494.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt="West Ham" src=https://static.bongda24h.vn/Medias/icon/2020/8/12/West-Ham.png>West Ham--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>-1</td>--%>
                                        <%--                                            <td>14</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/brentford-1617.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Brentford src=https://static.bongda24h.vn/Medias/icon/2022/09/27/brentford-2709105306.png>Brentford--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>13</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>11</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/chelsea-6.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Chelsea src=https://static.bongda24h.vn/Medias/icon/2020/7/23/Chelsea.png>Chelsea--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>12</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>12</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/wolverhampton-1458.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Wolves src=https://static.bongda24h.vn/Medias/icon/2020/8/12/Wolves.png>Wolves--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>-4</td>--%>
                                        <%--                                            <td>12</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>13</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/crystal-palace-1265.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt="Crystal Palace" src=https://static.bongda24h.vn/Medias/icon/2020/7/27/CrystalPalace.png>Crystal Palace--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>-5</td>--%>
                                        <%--                                            <td>12</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>14</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/fulham-9.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Fulham src=https://static.bongda24h.vn/Medias/icon/Fulham.png>Fulham--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>-7</td>--%>
                                        <%--                                            <td>12</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>15</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/everton-3.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Everton src=https://static.bongda24h.vn/Medias/icon/2020/8/11/Everton.png>Everton--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>6</td>--%>
                                        <%--                                            <td>-4</td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>16</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/nottingham-forest-1452.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt="Nottingham Forest" src=https://static.bongda24h.vn/Medias/icon/NottmForest_20141014180135.jpg>Nottingham Forest--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>-5</td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>17</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/bournemouth-1524.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Bournemouth src=https://static.bongda24h.vn/Medias/icon/2020/7/27/Bournemouth.png>Bournemouth--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>3</td>--%>
                                        <%--                                            <td>6</td>--%>
                                        <%--                                            <td>-13</td>--%>
                                        <%--                                            <td>6</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span title="Xuống hạng Championship" class="ranknum rank18">18</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/luton-town-2534.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt="Luton Town" src=https://static.bongda24h.vn/Medias/icon/2022/09/27/luton-2709154314.png>Luton Town--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>2</td>--%>
                                        <%--                                            <td>7</td>--%>
                                        <%--                                            <td>-11</td>--%>
                                        <%--                                            <td>5</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgyelow" title=Hòa>H</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span title="Xuống hạng Championship" class="ranknum rank19">19</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/burnley-1271.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt=Burnley src=https://static.bongda24h.vn/Medias/icon/2023/06/16/burnley-1606092510.jpg>Burnley--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>8</td>--%>
                                        <%--                                            <td>-17</td>--%>
                                        <%--                                            <td>4</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bggreen" title=Thắng>T</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span title="Xuống hạng Championship" class="ranknum rank20">20</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <a class=link-clb href=/clubs/sheffield-united-2437.html>--%>
                                        <%--                                                    <img class=bxhclb-icon alt="Sheffield United" src=https://static.bongda24h.vn/Medias/icon/2020/8/12/Sheffield-United.png>Sheffield United--%>
                                        <%--                                                </a>--%>
                                        <%--                                            </td>--%>
                                        <%--                                            <td>10</td>--%>
                                        <%--                                            <td>0</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>9</td>--%>
                                        <%--                                            <td>-22</td>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                                <span class="bg-color bgred" title=Bại>B</span>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>


                        <%--                        ///////////////////////////////////////////////--%>
                        <div class=color-info>
                            <div class=itemcolor>
                                <span class="bgcolor rank1"></span>
                                Dự vòng bảng Champions League
                            </div>
                            <div class=itemcolor>
                                <span class="bgcolor rank5"></span>
                                Dự vòng bảng Europa League
                            </div>
                            <div class=itemcolor>
                                <span class="bgcolor rank18"></span>
                                Xuống hạng Championship
                            </div>
                        </div>
                        <div class=color-info>
                            <ul class=ul-item>
                                <li>
                                    <span class="bg-color bggreen">T</span>
                                    Thắng
                                </li>
                                <li>
                                    <span class="bg-color bgyelow">H</span>
                                    Hòa
                                </li>
                                <li>
                                    <span class="bg-color bgred">B</span>
                                    Bại
                                </li>
                            </ul>
                        </div>
                        <div class="ads-center ads"></div>

                    </section>

                    <div class="ads-center ads"></div>
                </div>
            </section>
        </div>
    </div>
</main>
<footer class=footer>
    <div class=main-content>

        <div class=footer-copyright>
            <a href="/" title=bongda24h.vn>
                <img class="logo-address lazyload" alt=img-app src="data:image/gif;base64,R0lGODdhAQABAPAAAMPDwwAAACwAAAAAAQABAAACAkQBADs=" data-src=https://cdn.bongda24h.vn/images/logo-bongda24h.svg width=138 height=50>
            </a>
            <div class=footer-address>
                <p>© 2006. Trang thông tin điện tử tổng hợp Bongda24h.vn</p>
                <p>CƠ QUAN CHỦ QUẢN: Công ty Cổ phần Truyền thông Quốc tế INCOM</p>
                <p>Giấy phép số: 1183/GP-TTĐT cấp ngày 04/04/2016 bởi Sở TT-TT Hà Nội, thay thế giấy phép 258/GP-TTĐT cấp ngày 07/04/2011 bởi Sở TT-TT Hà Nội</p>
                <p>Nội dung thông tin hợp tác giữa Công ty INCOM và Báo điện tử Thể thao và Văn hoá - TTXVN.</p>
                <p>Chịu trách nhiệm: Ông Vũ Mạnh Cường</p>
                <p>Tòa soạn: Tầng 2, Tòa nhà IC, số 82 Duy Tân, Quận Cầu Giấy, TP. Hà Nội</p>
                <p>
                    Email: <a href="/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="4e2c2120292a2f7c7a260e27202d2123603820">[email &#160;protected]</a>
                </p>
                <div class="link-web dmca">
                    <a rel="nofollow noopener" target=_blank href=https://xoso.com.vn/xo-so-mien-bac/xsmb-p1.html title="XSMB Xo so mien bac">XSMB</a>
                    | <a rel="nofollow noopener" target=_blank href=https://lichngaytot.com/cung-hoang-dao.html title="Tu vi 12 cung hoang dao">12 Cung Hoang Dao</a>
                    | <a rel="nofollow noopener" target=_blank href=https://lichngaytot.com/xem-ngay-tot-xau.html title="Âm lịch hôm nay">Âm lịch hôm nay</a>
                </div>
            </div>
            <div class=footer-address-right>
                <div class=socal-0>
                    <a href=/RSS.html title=RSS>RSS</a>
                    | <span>Theo dõi chúng tôi</span>
                    <a target=_blank rel=noreferrer href="https://www.facebook.com/FanBongda24h.vn/" title=Fanpage>
                        <i class="fab fa-facebook-f socal-fa"></i>
                    </a>
                    <a target=_blank rel=noreferrer href=https://www.instagram.com/bongda24h.vn title=Instagram>
                        <i class="fab fa-instagram ic-instagram socal-fa"></i>
                    </a>
                    <a target=_blank rel=noreferrer href=https://www.youtube.com/channel/UCaJtPB6tgY9tdIV_ieMn36g title=Youtube>
                        <i class="fab fa-youtube socal-fa"></i>
                    </a>
                </div>
                <p>
                    <a class=icca title="Liên hệ tòa soạn" href=/lien-he-toa-soan.html>
                        <i class="far fa-envelope img-add"></i>
                        Tòa soạn
                    </a>
                    <a class=icca href=https://bongda24h.vn/bang-gia-quang-cao/lien-he-quang-cao-277-64252.html>
                        <i class="fab fa-adversal img-add"></i>
                        Quảng cáo
                    </a>
                    <span class=icca>
                                <i class="fas fa-phone-alt img-add"></i>
                                (024) 3.784 8888
                            </span>
                </p>
                <p>
                    Toàn bộ bản quyền thuộc <a href="/">Bongda24h.vn</a>
                </p>
                <p class=dmca>
                    <a href="https://www.dmca.com/Protection/Status.aspx?ID=012e60b4-e02e-4b2e-8420-df44c593724e&amp;refurl=https://bongda24h.vn/" target=_blank title="DMCA.com Protection Status" rel=noreferrer>
                        <img class=lazyload src="data:image/gif;base64,R0lGODdhAQABAPAAAMPDwwAAACwAAAAAAQABAAACAkQBADs=" data-src="//images.dmca.com/Badges/dmca_protected_16_120.png?ID=012e60b4-e02e-4b2e-8420-df44c593724e" alt="DMCA.com Protection Status" width=121 height=39>
                    </a>
                </p>
            </div>
        </div>
    </div>
</footer>
<a href=# class=backtotop>
    <img alt=top-arrow class=top-arrow src=https://cdn.bongda24h.vn/images/top-arrow.svg>
</a>
<div class=advstickyleft id=advLeftId></div>
<div class=advstickyright id=advRightId></div>
<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
<script src=https://cdn.bongda24h.vn/js/jquery.min.js></script>
<script src=https://cdn.bongda24h.vn/js/lazyload.min.js async></script>
<script src=https://cdn.bongda24h.vn/js/bongda24h.football.min.js async></script>
<script src=https://www.gstatic.com/firebasejs/8.3.0/firebase-app.js></script>
<script src=https://www.gstatic.com/firebasejs/8.3.0/firebase-messaging.js></script>
<script src=https://www.gstatic.com/firebasejs/8.3.0/firebase-analytics.js></script>
<script src="/js/firebase-init.min.js?v=AH99sKxcswtFlfUd00gzlEkzfGjKJxDo2ws-JC78vGo" async></script>
<script>
    var curentTitle = $('#titleLeague').html();
    var curentSeasion = $('#ddlSeason').val();
    var footballSeasonIdNow = '15';
    var liveScoreInterval;
    var timeInterval = 15000;
    function ChangeFootBallSeasonLive() {
        $.ajax({
            url: '/RankingTable/AjaxRankingTableByLeague',
            type: 'GET',
            data: {
                'leagueId': 1,
                'footballSeasonId': 15
            },
            success: function(data) {
                if (data.length) {
                    $('#AjaxContent').html(data);
                    if (value == curentSeasion) {
                        $('#titleLeague').html(curentTitle);
                    } else {
                        $('#titleLeague').html('BXH Ngoại hạng Anh (' + $('#ddlSeason  option:selected').text() + ')');
                    }
                }
            },
            error: function(xhr, status, error) {
                alert('Quý khách vui lòng thử lại sau.');
            }
        });
    }
    function ChangeFootBallSeason(value) {
        $.ajax({
            url: '/RankingTable/AjaxRankingTableByLeague',
            type: 'GET',
            data: {
                'leagueId': 1,
                'footballSeasonId': value
            },
            success: function(data) {
                if (data.length) {
                    $('#AjaxContent').html(data);
                    if (value == curentSeasion) {
                        $('#titleLeague').html(curentTitle);
                    } else {
                        $('#titleLeague').html('BXH Ngoại hạng Anh (' + $('#ddlSeason  option:selected').text() + ')');
                    }
                }
            },
            error: function(xhr, status, error) {
                alert('Quý khách vui lòng thử lại sau.');
            }
        });
    }
    function showLeague(leagueId) {
        $('#leaguerelate a').removeClass('active');
        $('a[data-leagueId = ' + leagueId + ']').addClass('active');
        if (leagueId == 0) {
            $('.leagueall').show();
        } else {
            $('.leagueall').hide();
            $('.league' + leagueId).show();
        }
    }
</script>
<script defer src="https://static.cloudflareinsights.com/beacon.min.js/v84a3a4012de94ce1a686ba8c167c359c1696973893317" integrity="sha512-euoFGowhlaLqXsPWQ48qSkBSCFs3DPRyiwVu3FjR96cMPx+Fr+gpWRhIafcHwqwCqWS42RZhIudOvEI+Ckf6MA==" data-cf-beacon='{"rayId":"81fa1b0429f31fc7","version":"2023.10.0","token":"84e2934211a74b0d94d325aa929e707e"}' crossorigin="anonymous"></script>
</body>
</html>
