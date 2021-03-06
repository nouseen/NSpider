package com.nouseen.weather.vo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nouseen.weather.properties.SpiderProperties;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class ChinaCities {

    private static Logger logger = Logger.getLogger(ChinaCities.class);
    public static String cities = "{'北京': ['北京'],\n" +
            "             '广东': ['广州', '深圳', '珠海', '汕头', '韶关', '佛山', '江门', '湛江', '茂名', '肇庆', '惠州', '梅州', '汕尾', '河源', '阳江', '清远', '东莞', '中山', '潮州', '揭阳', '云浮'],\n" +
            "             '上海': ['上海'],\n" +
            "             '天津': ['天津'],\n" +
            "             '重庆': ['重庆'],\n" +
            "             '辽宁': ['沈阳', '大连', '鞍山', '抚顺', '本溪', '丹东', '锦州', '营口', '阜新', '辽阳', '盘锦', '铁岭', '朝阳', '葫芦岛'],\n" +
            "             '江苏': ['南京', '苏州', '无锡', '常州', '镇江', '南通', '泰州', '扬州', '盐城', '连云港', '徐州', '淮安', '宿迁'],\n" +
            "             '湖北': ['武汉', '黄石', '十堰', '荆州', '宜昌', '襄樊', '鄂州', '荆门', '孝感', '黄冈', '咸宁', '随州', '恩施土家族苗族自治州', '仙桃', '天门', '潜江', '神农架林区'],\n" +
            "             '四川': ['成都', '自贡', '攀枝花', '泸州', '德阳', '绵阳', '广元', '遂宁', '内江', '乐山', '南充', '眉山', '宜宾', '广安', '达州', '雅安', '巴中', '资阳', '阿坝藏族羌族自治州', '甘孜藏族自治州', '凉山彝族自治州'],\n" +
            "             '陕西': ['西安', '铜川', '宝鸡', '咸阳', '渭南', '延安', '汉中', '榆林', '安康', '商洛'],\n" +
            "             '河北': ['石家庄', '唐山', '秦皇岛', '邯郸', '邢台', '保定', '张家口', '承德', '沧州', '廊坊', '衡水'],\n" +
            "             '山西': ['太原', '大同', '阳泉', '长治', '晋城', '朔州', '晋中', '运城', '忻州', '临汾', '吕梁'],\n" +
            "             '河南': ['郑州', '开封', '洛阳', '平顶山', '安阳', '鹤壁', '新乡', '焦作', '濮阳', '许昌', '漯河', '三门峡', '南阳', '商丘', '信阳', '周口', '驻马店'],\n" +
            "             '吉林': ['长春', '吉林', '四平', '辽源', '通化', '白山', '松原', '白城', '延边朝鲜族自治州'],\n" +
            "             '黑龙江': ['哈尔滨', '齐齐哈尔', '鹤岗', '双鸭山', '鸡西', '大庆', '伊春', '牡丹江', '佳木斯', '七台河', '黑河', '绥化', '大兴安岭地区'],\n" +
            "             '内蒙古': ['呼和浩特', '包头', '乌海', '赤峰', '通辽', '鄂尔多斯', '呼伦贝尔', '巴彦淖尔', '乌兰察布', '锡林郭勒盟', '兴安盟', '阿拉善盟'],\n" +
            "             '山东': ['济南', '青岛', '淄博', '枣庄', '东营', '烟台', '潍坊', '济宁', '泰安', '威海', '日照', '莱芜', '临沂', '德州', '聊城', '滨州', '菏泽'],\n" +
            "             '安徽': ['合肥', '芜湖', '蚌埠', '淮南', '马鞍山', '淮北', '铜陵', '安庆', '黄山', '滁州', '阜阳', '宿州', '巢湖', '六安', '亳州', '池州', '宣城'],\n" +
            "             '浙江': ['杭州', '宁波', '温州', '嘉兴', '湖州', '绍兴', '金华', '衢州', '舟山', '台州', '丽水'],\n" +
            "             '福建': ['福州', '厦门', '莆田', '三明', '泉州', '漳州', '南平', '龙岩', '宁德'],\n" +
            "             '湖南': ['长沙', '株洲', '湘潭', '衡阳', '邵阳', '岳阳', '常德', '张家界', '益阳', '郴州', '永州', '怀化', '娄底', '湘西土家族苗族自治州'],\n" +
            "             '广西': ['南宁', '柳州', '桂林', '梧州', '北海', '防城港', '钦州', '贵港', '玉林', '百色', '贺州', '河池', '来宾', '崇左'],\n" +
            "             '江西': ['南昌', '景德镇', '萍乡', '九江', '新余', '鹰潭', '赣州', '吉安', '宜春', '抚州', '上饶'],\n" +
            "             '贵州': ['贵阳', '六盘水', '遵义', '安顺', '铜仁地区', '毕节地区', '黔西南布依族苗族自治州', '黔东南苗族侗族自治州', '黔南布依族苗族自治州'],\n" +
            "             '云南': ['昆明', '曲靖', '玉溪', '保山', '昭通', '丽江', '普洱', '临沧', '德宏傣族景颇族自治州', '怒江傈僳族自治州', '迪庆藏族自治州', '大理白族自治州', '楚雄彝族自治州', '红河哈尼族彝族自治州', '文山壮族苗族自治州', '西双版纳傣族自治州'],\n" +
            "             '西藏': ['拉萨', '那曲地区', '昌都地区', '林芝地区', '山南地区', '日喀则地区', '阿里地区'],\n" +
            "             '海南': ['海口', '三亚', '五指山', '琼海', '儋州', '文昌', '万宁', '东方', '澄迈县', '定安县', '屯昌县', '临高县', '白沙黎族自治县', '昌江黎族自治县', '乐东黎族自治县', '陵水黎族自治县', '保亭黎族苗族自治县', '琼中黎族苗族自治县'],\n" +
            "             '甘肃': ['兰州', '嘉峪关', '金昌', '白银', '天水', '武威', '酒泉', '张掖', '庆阳', '平凉', '定西', '陇南', '临夏回族自治州', '甘南藏族自治州'],\n" +
            "             '宁夏': ['银川', '石嘴山', '吴忠', '固原', '中卫'],\n" +
            "             '青海': ['西宁', '海东地区', '海北藏族自治州', '海南藏族自治州', '黄南藏族自治州', '果洛藏族自治州', '玉树藏族自治州', '海西蒙古族藏族自治州'],\n" +
            "             '新疆': ['乌鲁木齐', '克拉玛依', '吐鲁番地区', '哈密地区', '和田地区', '阿克苏地区', '喀什地区', '克孜勒苏柯尔克孜自治州', '巴音郭楞蒙古自治州', '昌吉回族自治州', '博尔塔拉蒙古自治州', '石河子', '阿拉尔', '图木舒克', '五家渠', '伊犁哈萨克自治州'],\n" +
            "             '香港': ['香港'],\n" +
            "             '澳门': ['澳门'],\n" +
            "             '台湾': ['台北市', '高雄市', '台北县', '桃园县', '新竹县', '苗栗县', '台中县', '彰化县', '南投县', '云林县', '嘉义县', '台南县', '高雄县', '屏东县', '宜兰县', '花莲县', '台东县', '澎湖县', '基隆市', '新竹市', '台中市', '嘉义市', '台南市']}\n";


    public static String[][] citiesStringArray =
            {{"北京", "BEIJING", "BJ"}, {"上海", "SHANGHAI", "SH"}, {"天津", "TIANJIN", "TJ"}, {"重庆", "CHONGQING", "ZQ"}, {"阿克苏", "AKESU", "AKS"}, {"安宁", "ANNING", "AN"}, {"安庆", "ANQING", "AQ"}, {"鞍山", "ANSHAN", "AS"}, {"安顺", "ANSHUN", "AS"}, {"安阳", "ANYANG", "AY"}, {"白城", "BAICHENG", "BC"}, {"白山", "BAISHAN", "BS"}, {"白银", "BAIYIN", "BY"}, {"蚌埠", "BENGBU", "BB"}, {"保定", "BAODING", "BD"}, {"宝鸡", "BAOJI", "BJ"}, {"保山", "BAOSHAN", "BS"}, {"巴中", "BAZHONG", "BZ"}, {"北海", "BEIHAI", "BH"}, {"本溪", "BENXI", "BX"}, {"滨州", "BINZHOU", "BZ"}, {"博乐", "BOLE", "BL"}, {"亳州", "BOZHOU", "BZ"}, {"沧州", "CANGZHOU", "CZ"}, {"常德", "CHANGDE", "CD"}, {"昌吉", "CHANGJI", "CJ"}, {"常熟", "CHANGSHU", "CS"}, {"常州", "CHANGZHOU", "CZ"}, {"巢湖", "CHAOHU", "CH"}, {"朝阳", "CHAOYANG", "CY"}, {"潮州", "CHAOZHOU", "CZ"}, {"承德", "CHENGDE", "CD"}, {"成都", "CHENGDU", "CD"}, {"城固", "CHENGGU", "CG"}, {"郴州", "CHENZHOU", "CZ"}, {"赤壁", "CHIBI", "CB"}, {"赤峰", "CHIFENG", "CF"}, {"赤水", "CHISHUI", "CS"}, {"池州", "CHIZHOU", "CZ"}, {"崇左", "CHONGZUO", "CZ"}, {"楚雄", "CHUXIONG", "CX"}, {"滁州", "CHUZHOU", "CZ"}, {"慈溪", "CIXI", "CX"}, {"从化", "CONGHUA", "CH"}, {"大理", "DALI", "DL"}, {"大连", "DALIAN", "DL"}, {"丹东", "DANDONG", "DD"}, {"丹阳", "DANYANG", "DY"}, {"大庆", "DAQING", "DQ"}, {"大同", "DATONG", "DT"}, {"达州", "DAZHOU", "DZ"}, {"德阳", "DEYANG", "DY"}, {"德州", "DEZHOU", "DZ"}, {"东莞", "DONGGUAN", "DG"}, {"东阳", "DONGYANG", "DY"}, {"东营", "DONGYING", "DY"}, {"都匀", "DOUYUN", "DY"}, {"敦化", "DUNHUA", "DH"}, {"鄂尔多斯", "EERDUOSI", "EEDS"}, {"恩施", "ENSHI", "ES"}, {"防城港", "FANGCHENGGANG", "FCG"}, {"肥城", "FEICHENG", "FC"}, {"奉化", "FENGHUA", "FH"}, {"抚顺", "FUSHUN", "FS"}, {"阜新", "FUXIN", "FX"}, {"阜阳", "FUYANG", "FY"}, {"富阳", "FUYANG1", "FY"}, {"福州", "FUZHOU", "FZ"}, {"抚州", "FUZHOU1", "FZ"}, {"赣榆", "GANYU", "GY"}, {"赣州", "GANZHOU", "GZ"}, {"高明", "GAOMING", "GM"}, {"高邮", "GAOYOU", "GY"}, {"格尔木", "GEERMU", "GEM"}, {"个旧", "GEJIU", "GJ"}, {"巩义", "GONGYI", "GY"}, {"广安", "GUANGAN", "GA"}, {"广元", "GUANGYUAN", "GY"}, {"广州", "GUANGZHOU", "GZ"}, {"古包头", "GUBAOTOU", "GBT"}, {"贵港", "GUIGANG", "GG"}, {"桂林", "GUILIN", "GL"}, {"贵阳", "GUIYANG", "GY"}, {"固原", "GUYUAN", "GY"}, {"哈尔滨", "HAERBIN", "HEB"}, {"海城", "HAICHENG", "HC"}, {"海口", "HAIKOU", "HK"}, {"海门", "HAIMEN", "HM"}, {"海宁", "HAINING", "HN"}, {"哈密", "HAMI", "HM"}, {"邯郸", "HANDAN", "HD"}, {"杭州", "HANGZHOU", "HZ"}, {"汉中", "HANZHONG", "HZ"}, {"鹤壁", "HEBI", "HB"}, {"合肥", "HEFEI", "HF"}, {"衡水", "HENGSHUI", "HS"}, {"衡阳", "HENGYANG", "HY"}, {"和田", "HETIAN", "HT"}, {"河源", "HEYUAN", "HY"}, {"菏泽", "HEZE", "HZ"}, {"花都", "HUADOU", "HD"}, {"淮安", "HUAIAN", "HA"}, {"淮北", "HUAIBEI", "HB"}, {"怀化", "HUAIHUA", "HH"}, {"淮南", "HUAINAN", "HN"}, {"黄冈", "HUANGGANG", "HG"}, {"黄山", "HUANGSHAN", "HS"}, {"黄石", "HUANGSHI", "HS"}, {"呼和浩特", "HUHEHAOTE", "HHHT"}, {"惠州", "HUIZHOU", "HZ"}, {"葫芦岛", "HULUDAO", "HLD"}, {"湖州", "HUZHOU", "HZ"}, {"佳木斯", "JIAMUSI", "JMS"}, {"吉安", "JIAN", "JA"}, {"江都", "JIANGDOU", "JD"}, {"江门", "JIANGMEN", "JM"}, {"江阴", "JIANGYIN", "JY"}, {"胶南", "JIAONAN", "JN"}, {"胶州", "JIAOZHOU", "JZ"}, {"焦作", "JIAOZUO", "JZ"}, {"嘉善", "JIASHAN", "JS"}, {"嘉兴", "JIAXING", "JX"}, {"介休", "JIEXIU", "JX"}, {"吉林", "JILIN", "JL"}, {"即墨", "JIMO", "JM"}, {"济南", "JINAN", "JN"}, {"晋城", "JINCHENG", "JC"}, {"景德镇", "JINGDEZHEN", "JDZ"}, {"景洪", "JINGHONG", "JH"}, {"靖江", "JINGJIANG", "JJ"}, {"荆门", "JINGMEN", "JM"}, {"荆州", "JINGZHOU", "JZ"}, {"金华", "JINHUA", "JH"}, {"集宁", "JINING1", "JN"}, {"济宁", "JINING", "JN"}, {"晋江", "JINJIANG", "JJ"}, {"金坛", "JINTAN", "JT"}, {"晋中", "JINZHONG", "JZ"}, {"锦州", "JINZHOU", "JZ"}, {"吉首", "JISHOU", "JS"}, {"九江", "JIUJIANG", "JJ"}, {"酒泉", "JIUQUAN", "JQ"}, {"鸡西", "JIXI", "JX"}, {"济源", "JIYUAN", "JY"}, {"句容", "JURONG", "JR"}, {"开封", "KAIFENG", "KF"}, {"凯里", "KAILI", "KL"}, {"开平", "KAIPING", "KP"}, {"开远", "KAIYUAN", "KY"}, {"喀什", "KASHEN", "KS"}, {"克拉玛依", "KELAMAYI", "KLMY"}, {"库尔勒", "KUERLE", "KEL"}, {"奎屯", "KUITUN", "KT"}, {"昆明", "KUNMING", "KM"}, {"昆山", "KUNSHAN", "KS"}, {"来宾", "LAIBIN", "LB"}, {"莱芜", "LAIWU", "LW"}, {"莱西", "LAIXI", "LX"}, {"莱州", "LAIZHOU", "LZ"}, {"廊坊", "LANGFANG", "LF"}, {"兰州", "LANZHOU", "LZ"}, {"拉萨", "LASA", "LS"}, {"乐山", "LESHAN", "LS"}, {"连云港", "LIANYUNGANG", "LYG"}, {"聊城", "LIAOCHENG", "LC"}, {"辽阳", "LIAOYANG", "LY"}, {"辽源", "LIAOYUAN", "LY"}, {"丽江", "LIJIANG", "LJ"}, {"临安", "LINAN", "LA"}, {"临沧", "LINCANG", "LC"}, {"临汾", "LINFEN", "LF"}, {"灵宝", "LINGBAO", "LB"}, {"临河", "LINHE", "LH"}, {"临夏", "LINXIA", "LX"}, {"临沂", "LINYI", "LY"}, {"丽水", "LISHUI", "LS"}, {"六安", "LIUAN", "LA"}, {"六盘水", "LIUPANSHUI", "LPS"}, {"柳州", "LIUZHOU", "LZ"}, {"溧阳", "LIYANG", "LY"}, {"龙海", "LONGHAI", "LH"}, {"龙岩", "LONGYAN", "LY"}, {"娄底", "LOUDI", "LD"}, {"漯河", "LUOHE", "LH"}, {"洛阳", "LUOYANG", "LY"}, {"潞西", "LUXI", "LX"}, {"泸州", "LUZHOU", "LZ"}, {"吕梁", "LVLIANG", "LL"}, {"旅顺", "LVSHUN", "LS"}, {"马鞍山", "MAANSHAN", "MAS"}, {"茂名", "MAOMING", "MM"}, {"梅河口", "MEIHEKOU", "MHK"}, {"眉山", "MEISHAN", "MS"}, {"梅州", "MEIZHOU", "MZ"}, {"勉县", "MIANXIAN", "MX"}, {"绵阳", "MIANYANG", "MY"}, {"牡丹江", "MUDANJIANG", "MDJ"}, {"南安", "NANAN", "NA"}, {"南昌", "NANCHANG", "NC"}, {"南充", "NANCHONG", "NC"}, {"南京", "NANJING", "NJ"}, {"南宁", "NANNING", "NN"}, {"南平", "NANPING", "NP"}, {"南通", "NANTONG", "NT"}, {"南阳", "NANYANG", "NY"}, {"内江", "NEIJIANG", "NJ"}, {"宁波", "NINGBO", "NB"}, {"宁德", "NINGDE", "ND"}, {"盘锦", "PANJIN", "PJ"}, {"攀枝花", "PANZHIHUA", "PZH"}, {"蓬莱", "PENGLAI", "PL"}, {"平顶山", "PINGDINGSHAN", "PDS"}, {"平度", "PINGDU", "PD"}, {"平湖", "PINGHU", "PH"}, {"平凉", "PINGLIANG", "PL"}, {"萍乡", "PINGXIANG", "PX"}, {"普兰店", "PULANDIAN", "PLD"}, {"普宁", "PUNING", "PN"}, {"莆田", "PUTIAN", "PT"}, {"濮阳", "PUYANG", "PY"}, {"黔南", "QIANNAN", "QN"}, {"启东", "QIDONG", "QD"}, {"青岛", "QINGDAO", "QD"}, {"庆阳", "QINGYANG", "QY"}, {"清远", "QINGYUAN", "QY"}, {"青州", "QINGZHOU", "QZ"}, {"秦皇岛", "QINHUANGDAO", "QHD"}, {"钦州", "QINZHOU", "QZ"}, {"琼海", "QIONGHAI", "QH"}, {"齐齐哈尔", "QIQIHAER", "QQHE"}, {"泉州", "QUANZHOU", "QZ"}, {"曲靖", "QUJING", "QJ"}, {"衢州", "QUZHOU", "QZ"}, {"日喀则", "RIKAZE", "RKZ"}, {"日照", "RIZHAO", "RZ"}, {"荣成", "RONGCHENG", "RC"}, {"如皋", "RUGAO", "RG"}, {"瑞安", "RUIAN", "RA"}, {"乳山", "RUSHAN", "RS"}, {"三门峡", "SANMENXIA", "SMX"}, {"三明", "SANMING", "SM"}, {"三亚", "SANYA", "SY"}, {"厦门", "XIAMEN", "XM"}, {"佛山", "SHAN", "FS"}, {"商洛", "SHANGLUO", "SL"}, {"商丘", "SHANGQIU", "SQ"}, {"上饶", "SHANGRAO", "SR"}, {"上虞", "SHANGYU", "SY"}, {"汕头", "SHANTOU", "ST"}, {"安康", "ANKANG", "AK"}, {"韶关", "SHAOGUAN", "SG"}, {"绍兴", "SHAOXING", "SX"}, {"邵阳", "SHAOYANG", "SY"}, {"沈阳", "SHENYANG", "SY"}, {"深圳", "SHENZHEN", "SZ"}, {"石河子", "SHIHEZI", "SHZ"}, {"石家庄", "SHIJIAZHUANG", "SJZ"}, {"石林", "SHILIN", "SL"}, {"石狮", "SHISHI", "SS"}, {"十堰", "SHIYAN", "SY"}, {"寿光", "SHOUGUANG", "SG"}, {"双鸭山", "SHUANGYASHAN", "SYS"}, {"朔州", "SHUOZHOU", "SZ"}, {"沭阳", "SHUYANG", "SY"}, {"思茅", "SIMAO", "SM"}, {"四平", "SIPING", "SP"}, {"松原", "SONGYUAN", "SY"}, {"遂宁", "SUINING", "SN"}, {"随州", "SUIZHOU", "SZ"}, {"苏州", "SUZHOU", "SZ"}, {"塔城", "TACHENG", "TC"}, {"泰安", "TAIAN", "TA"}, {"太仓", "TAICANG", "TC"}, {"泰兴", "TAIXING", "TX"}, {"太原", "TAIYUAN", "TY"}, {"泰州", "TAIZHOU", "TZ"}, {"台州", "TAIZHOU1", "TZ"}, {"唐山", "TANGSHAN", "TS"}, {"腾冲", "TENGCHONG", "TC"}, {"滕州", "TENGZHOU", "TZ"}, {"天门", "TIANMEN", "TM"}, {"天水", "TIANSHUI", "TS"}, {"铁岭", "TIELING", "TL"}, {"铜川", "TONGCHUAN", "TC"}, {"通辽", "TONGLIAO", "TL"}, {"铜陵", "TONGLING", "TL"}, {"桐庐", "TONGLU", "TL"}, {"铜仁", "TONGREN", "TR"}, {"桐乡", "TONGXIANG", "TX"}, {"通州", "TONGZHOU", "TZ"}, {"通化", "TONGHUA", "TH"}, {"吐鲁番", "TULUFAN", "TLF"}, {"瓦房店", "WAFANGDIAN", "WFD"}, {"潍坊", "WEIFANG", "WF"}, {"威海", "WEIHAI", "WH"}, {"渭南", "WEINAN", "WN"}, {"文登", "WENDENG", "WD"}, {"温岭", "WENLING", "WL"}, {"温州", "WENZHOU", "WZ"}, {"乌海", "WUHAI", "WH"}, {"武汉", "WUHAN", "WH"}, {"芜湖", "WUHU", "WH"}, {"吴江", "WUJIANG", "WJ"}, {"乌兰浩特", "WULANHAOTE", "WLHT"}, {"武威", "WUWEI", "WW"}, {"无锡", "WUXI", "WX"}, {"梧州", "WUZHOU", "WZ"}, {"西安", "XIAN", "XA"}, {"项城", "XIANGCHENG", "XC"}, {"襄樊", "XIANGFAN", "XF"}, {"香格里拉", "XIANGGELILA", "XGLL"}, {"象山", "XIANGSHAN", "XS"}, {"湘潭", "XIANGTAN", "XT"}, {"湘乡", "XIANGXIANG", "XX"}, {"咸宁", "XIANNING", "XN"}, {"仙桃", "XIANTAO", "XT"}, {"咸阳", "XIANYANG", "XY"}, {"西藏", "XICANG", "XC"}, {"西昌", "XICHANG", "XC"}, {"邢台", "XINGTAI", "XT"}, {"兴义", "XINGYI", "XY"}, {"西宁", "XINING", "XN"}, {"新乡", "XINXIANG", "XX"}, {"信阳", "XINYANG", "XY"}, {"新余", "XINYU", "XY"}, {"忻州", "XINZHOU", "XZ"}, {"宿迁", "SUQIAN", "SQ"}, {"宿豫", "SUYU", "SY"}, {"宿州", "SUZHOU1", "SZ"}, {"宣城", "XUANCHENG", "XC"}, {"许昌", "XUCHANG", "XC"}, {"徐州", "XUZHOU", "XZ"}, {"雅安", "YAAN", "YA"}, {"牙克石", "YAKESHI", "YKS"}, {"延安", "YANAN", "YA"}, {"延边", "YANBIAN", "YB"}, {"盐城", "YANCHENG", "YC"}, {"阳江", "YANGJIANG", "YJ"}, {"阳泉", "YANGQUAN", "YQ"}, {"扬州", "YANGZHOU", "YZ"}, {"延吉", "YANJI", "YJ"}, {"烟台", "YANTAI", "YT"}, {"兖州", "YANZHOU", "YZ"}, {"宜宾", "YIBIN", "YB"}, {"宜昌", "YICHANG", "YC"}, {"宜春", "YICHUN", "YC"}, {"伊春", "YICHUN1", "YC"}, {"伊犁", "YILI", "YL"}, {"银川", "YINCHUAN", "YC"}, {"营口", "YINGKOU", "YK"}, {"鹰潭", "YINGTAN", "YT"}, {"伊宁", "YINING", "YN"}, {"义乌", "YIWU", "YW"}, {"宜兴", "YIXING", "YX"}, {"益阳", "YIYANG", "YY"}, {"永康", "YONGKANG", "YK"}, {"永州", "YONGZHOU", "YZ"}, {"岳阳", "YUEYANG", "YY"}, {"玉环", "YUHUAN", "YH"}, {"榆林", "YULIN1", "YL"}, {"玉林", "YULIN", "YL"}, {"运城", "YUNCHENG", "YC"}, {"玉溪", "YUXI", "YX"}, {"余姚", "YUYAO", "YY"}, {"枣庄", "ZAOZHUANG", "ZZ"}, {"增城", "ZENGCHENG", "ZC"}, {"长春", "CHANGCHUN", "CC"}, {"长海", "CHANGHAI", "CH"}, {"张家港", "ZHANGJIAGANG", "ZJG"}, {"张家界", "ZHANGJIAJIE", "ZJJ"}, {"张家口", "ZHANGJIAKOU", "ZJK"}, {"长乐 ", " CHANGLE ", "CL"}, {"章丘", "ZHANGQIU", "ZQ"}, {"长沙", "CHANGSHA", "CS"}, {"张掖", "ZHANGYE", "ZY"}, {"长治", "CHANGZHI", "CZ"}, {"漳州", "ZHANGZHOU", "ZZ"}, {"湛江", "ZHANJIANG", "ZJ"}, {"肇东", "ZHAODONG", "ZD"}, {"肇庆", "ZHAOQING", "ZQ"}, {"昭通", "ZHAOTONG", "ZT"}, {"郑州", "ZHENGZHOU", "ZZ"}, {"镇江", "ZHENJIANG", "ZJ"}, {"中山", "ZHONGSHAN", "ZS"}, {"周口", "ZHOUKOU", "ZK"}, {"舟山", "ZHOUSHAN", "ZS"}, {"诸城", "ZHUCHENG", "ZC"}, {"珠海", "ZHUHAI", "ZH"}, {"诸暨", "ZHUJI", "ZJ"}, {"驻马店", "ZHUMADIAN", "ZMD"}, {"株洲", "ZHUZHOU", "ZZ"}, {"淄博", "ZIBO", "ZB"}, {"自贡", "ZIGONG", "ZG"}, {"遵义", "ZUNYI", "ZY"}, {"乌鲁木齐", "WULUMUQI", "WLMQ"}, {"福清", "FUQING", "FQ"}, {"鄂州", "EZHOU", "EZ"}, {"包头", "BAOTOU", "BT"}, {"萧山", "XIAOSHAN", "XS"}, {"宣化", "XUANHUA", "XH"}, {"江油", "JIANGYOU", "JY"}, {"资阳", "ZIYANG", "ZY"}, {"辛集", "XINJI", "XJ"}, {"佛山", "FOSHAN", "FS"}, {"万州", "WANZHOU", "WZ"}, {"邹城", "ZOUCHENG", "ZC"}, {"邵武", "SHAOWU", "SW"}, {"姜堰", "JIANGYAN", "JY"}, {"湘阴", "XIANGYIN", "XY"}, {"松江", "SONGJIANG", "SJ"}, {"七台河", "QITAIHE", "QTH"}, {"醴陵", "LILING", "LL"}, {"涪陵", "FULING", "FL"}, {"公主岭", "GONGZHULING", "GZL"}, {"歙县", "SHEXIAN", "SX"}, {"兴化", "XINGHUA", "XH"}
            };

    public static String province4nmc = "[\n" +
            "    {\n" +
            "        \"code\": \"ABJ\",\n" +
            "        \"name\": \"北京市\",\n" +
            "        \"url\": \"/publish/forecast/ABJ.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ATJ\",\n" +
            "        \"name\": \"天津市\",\n" +
            "        \"url\": \"/publish/forecast/ATJ.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AHE\",\n" +
            "        \"name\": \"河北省\",\n" +
            "        \"url\": \"/publish/forecast/AHE.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ASX\",\n" +
            "        \"name\": \"山西省\",\n" +
            "        \"url\": \"/publish/forecast/ASX.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ANM\",\n" +
            "        \"name\": \"内蒙古自治区\",\n" +
            "        \"url\": \"/publish/forecast/ANM.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ALN\",\n" +
            "        \"name\": \"辽宁省\",\n" +
            "        \"url\": \"/publish/forecast/ALN.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AJL\",\n" +
            "        \"name\": \"吉林省\",\n" +
            "        \"url\": \"/publish/forecast/AJL.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AHL\",\n" +
            "        \"name\": \"黑龙江省\",\n" +
            "        \"url\": \"/publish/forecast/AHL.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ASH\",\n" +
            "        \"name\": \"上海市\",\n" +
            "        \"url\": \"/publish/forecast/ASH.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AJS\",\n" +
            "        \"name\": \"江苏省\",\n" +
            "        \"url\": \"/publish/forecast/AJS.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AZJ\",\n" +
            "        \"name\": \"浙江省\",\n" +
            "        \"url\": \"/publish/forecast/AZJ.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AAH\",\n" +
            "        \"name\": \"安徽省\",\n" +
            "        \"url\": \"/publish/forecast/AAH.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AFJ\",\n" +
            "        \"name\": \"福建省\",\n" +
            "        \"url\": \"/publish/forecast/AFJ.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AJX\",\n" +
            "        \"name\": \"江西省\",\n" +
            "        \"url\": \"/publish/forecast/AJX.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ASD\",\n" +
            "        \"name\": \"山东省\",\n" +
            "        \"url\": \"/publish/forecast/ASD.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AHA\",\n" +
            "        \"name\": \"河南省\",\n" +
            "        \"url\": \"/publish/forecast/AHA.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AHB\",\n" +
            "        \"name\": \"湖北省\",\n" +
            "        \"url\": \"/publish/forecast/AHB.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AHN\",\n" +
            "        \"name\": \"湖南省\",\n" +
            "        \"url\": \"/publish/forecast/AHN.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AGD\",\n" +
            "        \"name\": \"广东省\",\n" +
            "        \"url\": \"/publish/forecast/AGD.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AGX\",\n" +
            "        \"name\": \"广西壮族自治区\",\n" +
            "        \"url\": \"/publish/forecast/AGX.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AHI\",\n" +
            "        \"name\": \"海南省\",\n" +
            "        \"url\": \"/publish/forecast/AHI.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ACQ\",\n" +
            "        \"name\": \"重庆市\",\n" +
            "        \"url\": \"/publish/forecast/ACQ.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ASC\",\n" +
            "        \"name\": \"四川省\",\n" +
            "        \"url\": \"/publish/forecast/ASC.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AGZ\",\n" +
            "        \"name\": \"贵州省\",\n" +
            "        \"url\": \"/publish/forecast/AGZ.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AYN\",\n" +
            "        \"name\": \"云南省\",\n" +
            "        \"url\": \"/publish/forecast/AYN.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AXZ\",\n" +
            "        \"name\": \"西藏自治区\",\n" +
            "        \"url\": \"/publish/forecast/AXZ.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ASN\",\n" +
            "        \"name\": \"陕西省\",\n" +
            "        \"url\": \"/publish/forecast/ASN.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AGS\",\n" +
            "        \"name\": \"甘肃省\",\n" +
            "        \"url\": \"/publish/forecast/AGS.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AQH\",\n" +
            "        \"name\": \"青海省\",\n" +
            "        \"url\": \"/publish/forecast/AQH.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ANX\",\n" +
            "        \"name\": \"宁夏回族自治区\",\n" +
            "        \"url\": \"/publish/forecast/ANX.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AXJ\",\n" +
            "        \"name\": \"新疆维吾尔自治区\",\n" +
            "        \"url\": \"/publish/forecast/AXJ.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AXG\",\n" +
            "        \"name\": \"香港特别行政区\",\n" +
            "        \"url\": \"/publish/forecast/AXG.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"AAM\",\n" +
            "        \"name\": \"澳门特别行政区\",\n" +
            "        \"url\": \"/publish/forecast/AAM.html\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": \"ATW\",\n" +
            "        \"name\": \"台湾省\",\n" +
            "        \"url\": \"/publish/forecast/ATW.html\"\n" +
            "    }\n" +
            "]";

    private static Map<String, NmcCity> nmcCityCodeMap = Maps.newHashMap();

    public static NmcCity getNmcCityByCode(String code) {
        return nmcCityCodeMap.get(code);
    }

    public static List<NmcCity> getChinaCities4nmc() {
        List<NmcCity> nmcCities = Lists.newLinkedList();

        try {
            InputStream resourceAsStream = ChinaCities.class.getResourceAsStream(SpiderProperties.CitesDataName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream,"UTF-8"));

            char[] chars = new char[1024];


            String line = "";
            String citiesJson = "";
            int num;
            while ((line = bufferedReader.readLine()) != null) {
                citiesJson += line;
            };

            bufferedReader.close();

            nmcCities = JSON.parseArray(citiesJson, NmcCity.class);

            for (NmcCity nmcCity : nmcCities) {
                nmcCityCodeMap.put(nmcCity.getCode(), nmcCity);
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return nmcCities;
    }
}