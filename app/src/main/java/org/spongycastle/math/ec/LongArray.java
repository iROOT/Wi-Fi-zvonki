package org.spongycastle.math.ec;

import android.support.v4.app.FragmentTransaction;
import java.math.BigInteger;
import net.hockeyapp.android.k;
import net.hockeyapp.android.views.UpdateView;
import org.spongycastle.util.Arrays;

class LongArray {
    static final byte[] a = new byte[]{(byte) 0, (byte) 1, (byte) 2, (byte) 2, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 8};
    private static final int[] b = new int[]{0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, k.FEEDBACK_FAILED_TITLE_ID, k.FEEDBACK_FAILED_TEXT_ID, k.FEEDBACK_SUBJECT_INPUT_HINT_ID, k.FEEDBACK_MESSAGE_INPUT_HINT_ID, k.FEEDBACK_GENERIC_ERROR_ID, k.FEEDBACK_VALIDATE_NAME_ERROR_ID, k.FEEDBACK_ATTACH_FILE_ID, k.FEEDBACK_ATTACH_PICTURE_ID, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, k.LOGIN_HEADLINE_TEXT_ID, k.LOGIN_MISSING_CREDENTIALS_TOAST_ID, k.LOGIN_LOGIN_BUTTON_TEXT_ID, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, FragmentTransaction.TRANSIT_ENTER_MASK, 4097, UpdateView.UPDATE_BUTTON_ID, UpdateView.WEB_VIEW_ID, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, 16384, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    private static final int[] c = new int[]{0, 1, 8, 9, 64, 65, 72, 73, 512, k.UPDATE_DIALOG_TITLE_ID, 520, 521, 576, 577, 584, 585, FragmentTransaction.TRANSIT_ENTER_MASK, 4097, 4104, 4105, 4160, 4161, 4168, 4169, 4608, 4609, 4616, 4617, 4672, 4673, 4680, 4681, 32768, 32769, 32776, 32777, 32832, 32833, 32840, 32841, 33280, 33281, 33288, 33289, 33344, 33345, 33352, 33353, 36864, 36865, 36872, 36873, 36928, 36929, 36936, 36937, 37376, 37377, 37384, 37385, 37440, 37441, 37448, 37449, 262144, 262145, 262152, 262153, 262208, 262209, 262216, 262217, 262656, 262657, 262664, 262665, 262720, 262721, 262728, 262729, 266240, 266241, 266248, 266249, 266304, 266305, 266312, 266313, 266752, 266753, 266760, 266761, 266816, 266817, 266824, 266825, 294912, 294913, 294920, 294921, 294976, 294977, 294984, 294985, 295424, 295425, 295432, 295433, 295488, 295489, 295496, 295497, 299008, 299009, 299016, 299017, 299072, 299073, 299080, 299081, 299520, 299521, 299528, 299529, 299584, 299585, 299592, 299593};
    private static final int[] d = new int[]{0, 1, 16, 17, 256, k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID, 272, 273, FragmentTransaction.TRANSIT_ENTER_MASK, 4097, 4112, 4113, 4352, 4353, 4368, 4369, 65536, 65537, 65552, 65553, 65792, 65793, 65808, 65809, 69632, 69633, 69648, 69649, 69888, 69889, 69904, 69905, 1048576, 1048577, 1048592, 1048593, 1048832, 1048833, 1048848, 1048849, 1052672, 1052673, 1052688, 1052689, 1052928, 1052929, 1052944, 1052945, 1114112, 1114113, 1114128, 1114129, 1114368, 1114369, 1114384, 1114385, 1118208, 1118209, 1118224, 1118225, 1118464, 1118465, 1118480, 1118481, 16777216, 16777217, 16777232, 16777233, 16777472, 16777473, 16777488, 16777489, 16781312, 16781313, 16781328, 16781329, 16781568, 16781569, 16781584, 16781585, 16842752, 16842753, 16842768, 16842769, 16843008, 16843009, 16843024, 16843025, 16846848, 16846849, 16846864, 16846865, 16847104, 16847105, 16847120, 16847121, 17825792, 17825793, 17825808, 17825809, 17826048, 17826049, 17826064, 17826065, 17829888, 17829889, 17829904, 17829905, 17830144, 17830145, 17830160, 17830161, 17891328, 17891329, 17891344, 17891345, 17891584, 17891585, 17891600, 17891601, 17895424, 17895425, 17895440, 17895441, 17895680, 17895681, 17895696, 17895697, 268435456, 268435457, 268435472, 268435473, 268435712, 268435713, 268435728, 268435729, 268439552, 268439553, 268439568, 268439569, 268439808, 268439809, 268439824, 268439825, 268500992, 268500993, 268501008, 268501009, 268501248, 268501249, 268501264, 268501265, 268505088, 268505089, 268505104, 268505105, 268505344, 268505345, 268505360, 268505361, 269484032, 269484033, 269484048, 269484049, 269484288, 269484289, 269484304, 269484305, 269488128, 269488129, 269488144, 269488145, 269488384, 269488385, 269488400, 269488401, 269549568, 269549569, 269549584, 269549585, 269549824, 269549825, 269549840, 269549841, 269553664, 269553665, 269553680, 269553681, 269553920, 269553921, 269553936, 269553937, 285212672, 285212673, 285212688, 285212689, 285212928, 285212929, 285212944, 285212945, 285216768, 285216769, 285216784, 285216785, 285217024, 285217025, 285217040, 285217041, 285278208, 285278209, 285278224, 285278225, 285278464, 285278465, 285278480, 285278481, 285282304, 285282305, 285282320, 285282321, 285282560, 285282561, 285282576, 285282577, 286261248, 286261249, 286261264, 286261265, 286261504, 286261505, 286261520, 286261521, 286265344, 286265345, 286265360, 286265361, 286265600, 286265601, 286265616, 286265617, 286326784, 286326785, 286326800, 286326801, 286327040, 286327041, 286327056, 286327057, 286330880, 286330881, 286330896, 286330897, 286331136, 286331137, 286331152, 286331153};
    private static final int[] e = new int[]{0, 1, 32, 33, k.FEEDBACK_FAILED_TITLE_ID, k.FEEDBACK_FAILED_TEXT_ID, 1056, 1057, 32768, 32769, 32800, 32801, 33792, 33793, 33824, 33825, 1048576, 1048577, 1048608, 1048609, 1049600, 1049601, 1049632, 1049633, 1081344, 1081345, 1081376, 1081377, 1082368, 1082369, 1082400, 1082401, 33554432, 33554433, 33554464, 33554465, 33555456, 33555457, 33555488, 33555489, 33587200, 33587201, 33587232, 33587233, 33588224, 33588225, 33588256, 33588257, 34603008, 34603009, 34603040, 34603041, 34604032, 34604033, 34604064, 34604065, 34635776, 34635777, 34635808, 34635809, 34636800, 34636801, 34636832, 34636833, 1073741824, 1073741825, 1073741856, 1073741857, 1073742848, 1073742849, 1073742880, 1073742881, 1073774592, 1073774593, 1073774624, 1073774625, 1073775616, 1073775617, 1073775648, 1073775649, 1074790400, 1074790401, 1074790432, 1074790433, 1074791424, 1074791425, 1074791456, 1074791457, 1074823168, 1074823169, 1074823200, 1074823201, 1074824192, 1074824193, 1074824224, 1074824225, 1107296256, 1107296257, 1107296288, 1107296289, 1107297280, 1107297281, 1107297312, 1107297313, 1107329024, 1107329025, 1107329056, 1107329057, 1107330048, 1107330049, 1107330080, 1107330081, 1108344832, 1108344833, 1108344864, 1108344865, 1108345856, 1108345857, 1108345888, 1108345889, 1108377600, 1108377601, 1108377632, 1108377633, 1108378624, 1108378625, 1108378656, 1108378657};
    private static final long[] f = new long[]{0, 1, 128, 129, 16384, 16385, 16512, 16513, 2097152, 2097153, 2097280, 2097281, 2113536, 2113537, 2113664, 2113665, 268435456, 268435457, 268435584, 268435585, 268451840, 268451841, 268451968, 268451969, 270532608, 270532609, 270532736, 270532737, 270548992, 270548993, 270549120, 270549121, 34359738368L, 34359738369L, 34359738496L, 34359738497L, 34359754752L, 34359754753L, 34359754880L, 34359754881L, 34361835520L, 34361835521L, 34361835648L, 34361835649L, 34361851904L, 34361851905L, 34361852032L, 34361852033L, 34628173824L, 34628173825L, 34628173952L, 34628173953L, 34628190208L, 34628190209L, 34628190336L, 34628190337L, 34630270976L, 34630270977L, 34630271104L, 34630271105L, 34630287360L, 34630287361L, 34630287488L, 34630287489L, 4398046511104L, 4398046511105L, 4398046511232L, 4398046511233L, 4398046527488L, 4398046527489L, 4398046527616L, 4398046527617L, 4398048608256L, 4398048608257L, 4398048608384L, 4398048608385L, 4398048624640L, 4398048624641L, 4398048624768L, 4398048624769L, 4398314946560L, 4398314946561L, 4398314946688L, 4398314946689L, 4398314962944L, 4398314962945L, 4398314963072L, 4398314963073L, 4398317043712L, 4398317043713L, 4398317043840L, 4398317043841L, 4398317060096L, 4398317060097L, 4398317060224L, 4398317060225L, 4432406249472L, 4432406249473L, 4432406249600L, 4432406249601L, 4432406265856L, 4432406265857L, 4432406265984L, 4432406265985L, 4432408346624L, 4432408346625L, 4432408346752L, 4432408346753L, 4432408363008L, 4432408363009L, 4432408363136L, 4432408363137L, 4432674684928L, 4432674684929L, 4432674685056L, 4432674685057L, 4432674701312L, 4432674701313L, 4432674701440L, 4432674701441L, 4432676782080L, 4432676782081L, 4432676782208L, 4432676782209L, 4432676798464L, 4432676798465L, 4432676798592L, 4432676798593L, 562949953421312L, 562949953421313L, 562949953421440L, 562949953421441L, 562949953437696L, 562949953437697L, 562949953437824L, 562949953437825L, 562949955518464L, 562949955518465L, 562949955518592L, 562949955518593L, 562949955534848L, 562949955534849L, 562949955534976L, 562949955534977L, 562950221856768L, 562950221856769L, 562950221856896L, 562950221856897L, 562950221873152L, 562950221873153L, 562950221873280L, 562950221873281L, 562950223953920L, 562950223953921L, 562950223954048L, 562950223954049L, 562950223970304L, 562950223970305L, 562950223970432L, 562950223970433L, 562984313159680L, 562984313159681L, 562984313159808L, 562984313159809L, 562984313176064L, 562984313176065L, 562984313176192L, 562984313176193L, 562984315256832L, 562984315256833L, 562984315256960L, 562984315256961L, 562984315273216L, 562984315273217L, 562984315273344L, 562984315273345L, 562984581595136L, 562984581595137L, 562984581595264L, 562984581595265L, 562984581611520L, 562984581611521L, 562984581611648L, 562984581611649L, 562984583692288L, 562984583692289L, 562984583692416L, 562984583692417L, 562984583708672L, 562984583708673L, 562984583708800L, 562984583708801L, 567347999932416L, 567347999932417L, 567347999932544L, 567347999932545L, 567347999948800L, 567347999948801L, 567347999948928L, 567347999948929L, 567348002029568L, 567348002029569L, 567348002029696L, 567348002029697L, 567348002045952L, 567348002045953L, 567348002046080L, 567348002046081L, 567348268367872L, 567348268367873L, 567348268368000L, 567348268368001L, 567348268384256L, 567348268384257L, 567348268384384L, 567348268384385L, 567348270465024L, 567348270465025L, 567348270465152L, 567348270465153L, 567348270481408L, 567348270481409L, 567348270481536L, 567348270481537L, 567382359670784L, 567382359670785L, 567382359670912L, 567382359670913L, 567382359687168L, 567382359687169L, 567382359687296L, 567382359687297L, 567382361767936L, 567382361767937L, 567382361768064L, 567382361768065L, 567382361784320L, 567382361784321L, 567382361784448L, 567382361784449L, 567382628106240L, 567382628106241L, 567382628106368L, 567382628106369L, 567382628122624L, 567382628122625L, 567382628122752L, 567382628122753L, 567382630203392L, 567382630203393L, 567382630203520L, 567382630203521L, 567382630219776L, 567382630219777L, 567382630219904L, 567382630219905L, 72057594037927936L, 72057594037927937L, 72057594037928064L, 72057594037928065L, 72057594037944320L, 72057594037944321L, 72057594037944448L, 72057594037944449L, 72057594040025088L, 72057594040025089L, 72057594040025216L, 72057594040025217L, 72057594040041472L, 72057594040041473L, 72057594040041600L, 72057594040041601L, 72057594306363392L, 72057594306363393L, 72057594306363520L, 72057594306363521L, 72057594306379776L, 72057594306379777L, 72057594306379904L, 72057594306379905L, 72057594308460544L, 72057594308460545L, 72057594308460672L, 72057594308460673L, 72057594308476928L, 72057594308476929L, 72057594308477056L, 72057594308477057L, 72057628397666304L, 72057628397666305L, 72057628397666432L, 72057628397666433L, 72057628397682688L, 72057628397682689L, 72057628397682816L, 72057628397682817L, 72057628399763456L, 72057628399763457L, 72057628399763584L, 72057628399763585L, 72057628399779840L, 72057628399779841L, 72057628399779968L, 72057628399779969L, 72057628666101760L, 72057628666101761L, 72057628666101888L, 72057628666101889L, 72057628666118144L, 72057628666118145L, 72057628666118272L, 72057628666118273L, 72057628668198912L, 72057628668198913L, 72057628668199040L, 72057628668199041L, 72057628668215296L, 72057628668215297L, 72057628668215424L, 72057628668215425L, 72061992084439040L, 72061992084439041L, 72061992084439168L, 72061992084439169L, 72061992084455424L, 72061992084455425L, 72061992084455552L, 72061992084455553L, 72061992086536192L, 72061992086536193L, 72061992086536320L, 72061992086536321L, 72061992086552576L, 72061992086552577L, 72061992086552704L, 72061992086552705L, 72061992352874496L, 72061992352874497L, 72061992352874624L, 72061992352874625L, 72061992352890880L, 72061992352890881L, 72061992352891008L, 72061992352891009L, 72061992354971648L, 72061992354971649L, 72061992354971776L, 72061992354971777L, 72061992354988032L, 72061992354988033L, 72061992354988160L, 72061992354988161L, 72062026444177408L, 72062026444177409L, 72062026444177536L, 72062026444177537L, 72062026444193792L, 72062026444193793L, 72062026444193920L, 72062026444193921L, 72062026446274560L, 72062026446274561L, 72062026446274688L, 72062026446274689L, 72062026446290944L, 72062026446290945L, 72062026446291072L, 72062026446291073L, 72062026712612864L, 72062026712612865L, 72062026712612992L, 72062026712612993L, 72062026712629248L, 72062026712629249L, 72062026712629376L, 72062026712629377L, 72062026714710016L, 72062026714710017L, 72062026714710144L, 72062026714710145L, 72062026714726400L, 72062026714726401L, 72062026714726528L, 72062026714726529L, 72620543991349248L, 72620543991349249L, 72620543991349376L, 72620543991349377L, 72620543991365632L, 72620543991365633L, 72620543991365760L, 72620543991365761L, 72620543993446400L, 72620543993446401L, 72620543993446528L, 72620543993446529L, 72620543993462784L, 72620543993462785L, 72620543993462912L, 72620543993462913L, 72620544259784704L, 72620544259784705L, 72620544259784832L, 72620544259784833L, 72620544259801088L, 72620544259801089L, 72620544259801216L, 72620544259801217L, 72620544261881856L, 72620544261881857L, 72620544261881984L, 72620544261881985L, 72620544261898240L, 72620544261898241L, 72620544261898368L, 72620544261898369L, 72620578351087616L, 72620578351087617L, 72620578351087744L, 72620578351087745L, 72620578351104000L, 72620578351104001L, 72620578351104128L, 72620578351104129L, 72620578353184768L, 72620578353184769L, 72620578353184896L, 72620578353184897L, 72620578353201152L, 72620578353201153L, 72620578353201280L, 72620578353201281L, 72620578619523072L, 72620578619523073L, 72620578619523200L, 72620578619523201L, 72620578619539456L, 72620578619539457L, 72620578619539584L, 72620578619539585L, 72620578621620224L, 72620578621620225L, 72620578621620352L, 72620578621620353L, 72620578621636608L, 72620578621636609L, 72620578621636736L, 72620578621636737L, 72624942037860352L, 72624942037860353L, 72624942037860480L, 72624942037860481L, 72624942037876736L, 72624942037876737L, 72624942037876864L, 72624942037876865L, 72624942039957504L, 72624942039957505L, 72624942039957632L, 72624942039957633L, 72624942039973888L, 72624942039973889L, 72624942039974016L, 72624942039974017L, 72624942306295808L, 72624942306295809L, 72624942306295936L, 72624942306295937L, 72624942306312192L, 72624942306312193L, 72624942306312320L, 72624942306312321L, 72624942308392960L, 72624942308392961L, 72624942308393088L, 72624942308393089L, 72624942308409344L, 72624942308409345L, 72624942308409472L, 72624942308409473L, 72624976397598720L, 72624976397598721L, 72624976397598848L, 72624976397598849L, 72624976397615104L, 72624976397615105L, 72624976397615232L, 72624976397615233L, 72624976399695872L, 72624976399695873L, 72624976399696000L, 72624976399696001L, 72624976399712256L, 72624976399712257L, 72624976399712384L, 72624976399712385L, 72624976666034176L, 72624976666034177L, 72624976666034304L, 72624976666034305L, 72624976666050560L, 72624976666050561L, 72624976666050688L, 72624976666050689L, 72624976668131328L, 72624976668131329L, 72624976668131456L, 72624976668131457L, 72624976668147712L, 72624976668147713L, 72624976668147840L, 72624976668147841L};
    private long[] g;

    public LongArray(int i) {
        this.g = new long[i];
    }

    public LongArray(long[] jArr) {
        this.g = jArr;
    }

    public LongArray(long[] jArr, int i, int i2) {
        if (i == 0 && i2 == jArr.length) {
            this.g = jArr;
            return;
        }
        this.g = new long[i2];
        System.arraycopy(jArr, i, this.g, 0, i2);
    }

    public LongArray(BigInteger bigInteger) {
        int i = 1;
        if (bigInteger == null || bigInteger.signum() < 0) {
            throw new IllegalArgumentException("invalid F2m field value");
        } else if (bigInteger.signum() == 0) {
            this.g = new long[]{0};
        } else {
            long j;
            int i2;
            byte[] toByteArray = bigInteger.toByteArray();
            int length = toByteArray.length;
            if (toByteArray[0] == (byte) 0) {
                length--;
            } else {
                i = 0;
            }
            int i3 = (length + 7) / 8;
            this.g = new long[i3];
            i3--;
            length = (length % 8) + i;
            if (i < length) {
                j = 0;
                while (i < length) {
                    j = (j << 8) | ((long) (toByteArray[i] & 255));
                    i++;
                }
                length = i3 - 1;
                this.g[i3] = j;
                i2 = length;
            } else {
                i2 = i3;
            }
            while (i2 >= 0) {
                length = i;
                j = 0;
                i = 0;
                while (i < 8) {
                    j = (j << 8) | ((long) (toByteArray[length] & 255));
                    i++;
                    length++;
                }
                this.g[i2] = j;
                i2--;
                i = length;
            }
        }
    }

    public boolean a() {
        long[] jArr = this.g;
        for (long j : jArr) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public int b() {
        return a(this.g.length);
    }

    public int a(int i) {
        long[] jArr = this.g;
        int min = Math.min(i, jArr.length);
        if (min < 1) {
            return 0;
        }
        if (jArr[0] != 0) {
            do {
                min--;
            } while (jArr[min] == 0);
            return min + 1;
        }
        do {
            min--;
            if (jArr[min] != 0) {
                return min + 1;
            }
        } while (min > 0);
        return 0;
    }

    public int c() {
        int length = this.g.length;
        while (length != 0) {
            length--;
            long j = this.g[length];
            if (j != 0) {
                return (length << 6) + a(j);
            }
        }
        return 0;
    }

    private int b(int i) {
        int i2 = (i + 62) >>> 6;
        while (i2 != 0) {
            i2--;
            long j = this.g[i2];
            if (j != 0) {
                return (i2 << 6) + a(j);
            }
        }
        return 0;
    }

    private static int a(long j) {
        int i;
        int i2 = (int) (j >>> 32);
        if (i2 == 0) {
            i = (int) j;
            i2 = 0;
        } else {
            i = i2;
            i2 = 32;
        }
        int i3 = i >>> 16;
        if (i3 == 0) {
            i3 = i >>> 8;
            i = i3 == 0 ? a[i] : a[i3] + 8;
        } else {
            i = i3 >>> 8;
            i = i == 0 ? a[i3] + 16 : a[i] + 24;
        }
        return i + i2;
    }

    private long[] c(int i) {
        Object obj = new long[i];
        System.arraycopy(this.g, 0, obj, 0, Math.min(this.g.length, i));
        return obj;
    }

    public BigInteger d() {
        int b = b();
        if (b == 0) {
            return ECConstants.c;
        }
        int i;
        long j = this.g[b - 1];
        byte[] bArr = new byte[8];
        int i2 = 0;
        int i3 = 0;
        for (i = 7; i >= 0; i--) {
            byte b2 = (byte) ((int) (j >>> (i * 8)));
            if (i2 != 0 || b2 != (byte) 0) {
                i2 = i3 + 1;
                bArr[i3] = b2;
                i3 = i2;
                i2 = 1;
            }
        }
        byte[] bArr2 = new byte[(((b - 1) * 8) + i3)];
        for (i2 = 0; i2 < i3; i2++) {
            bArr2[i2] = bArr[i2];
        }
        i = b - 2;
        i2 = i3;
        while (i >= 0) {
            long j2 = this.g[i];
            i3 = i2;
            i2 = 7;
            while (i2 >= 0) {
                int i4 = i3 + 1;
                bArr2[i3] = (byte) ((int) (j2 >>> (i2 * 8)));
                i2--;
                i3 = i4;
            }
            i--;
            i2 = i3;
        }
        return new BigInteger(1, bArr2);
    }

    private static long a(long[] jArr, int i, long[] jArr2, int i2, int i3, int i4) {
        int i5 = 64 - i4;
        long j = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            long j2 = jArr[i + i6];
            jArr2[i2 + i6] = j | (j2 << i4);
            j = j2 >>> i5;
        }
        return j;
    }

    public LongArray e() {
        if (this.g.length == 0) {
            return new LongArray(new long[]{1});
        }
        long[] c = c(Math.max(1, b()));
        c[0] = c[0] ^ 1;
        return new LongArray(c);
    }

    private void a(LongArray longArray, int i, int i2) {
        int i3 = (i + 63) >>> 6;
        int i4 = i2 >>> 6;
        int i5 = i2 & 63;
        if (i5 == 0) {
            a(this.g, i4, longArray.g, 0, i3);
            return;
        }
        long b = b(this.g, i4, longArray.g, 0, i3, i5);
        if (b != 0) {
            long[] jArr = this.g;
            i4 += i3;
            jArr[i4] = b ^ jArr[i4];
        }
    }

    private static long b(long[] jArr, int i, long[] jArr2, int i2, int i3, int i4) {
        int i5 = 64 - i4;
        long j = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            long j2 = jArr2[i2 + i6];
            int i7 = i + i6;
            jArr[i7] = (j | (j2 << i4)) ^ jArr[i7];
            j = j2 >>> i5;
        }
        return j;
    }

    private static long c(long[] jArr, int i, long[] jArr2, int i2, int i3, int i4) {
        int i5 = 64 - i4;
        long j = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                return j;
            }
            long j2 = jArr2[i2 + i3];
            int i6 = i + i3;
            jArr[i6] = (j | (j2 >>> i4)) ^ jArr[i6];
            j = j2 << i5;
        }
    }

    public void a(LongArray longArray, int i) {
        int b = longArray.b();
        if (b != 0) {
            int i2 = b + i;
            if (i2 > this.g.length) {
                this.g = c(i2);
            }
            a(this.g, i, longArray.g, 0, b);
        }
    }

    private static void a(long[] jArr, int i, long[] jArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            jArr[i5] = jArr[i5] ^ jArr2[i2 + i4];
        }
    }

    private static void a(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            jArr3[i3 + i5] = jArr[i + i5] ^ jArr2[i2 + i5];
        }
    }

    private static void b(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i + i5;
            jArr[i6] = jArr[i6] ^ (jArr2[i2 + i5] ^ jArr3[i3 + i5]);
        }
    }

    private static void a(long[] jArr, int i, int i2, long j) {
        int i3 = (i2 >>> 6) + i;
        int i4 = i2 & 63;
        if (i4 == 0) {
            jArr[i3] = jArr[i3] ^ j;
            return;
        }
        jArr[i3] = jArr[i3] ^ (j << i4);
        long j2 = j >>> (64 - i4);
        if (j2 != 0) {
            i3++;
            jArr[i3] = j2 ^ jArr[i3];
        }
    }

    public boolean f() {
        return this.g.length > 0 && (this.g[0] & 1) != 0;
    }

    private static boolean a(long[] jArr, int i, int i2) {
        return (jArr[(i2 >>> 6) + i] & (1 << (i2 & 63))) != 0;
    }

    private static void b(long[] jArr, int i, int i2) {
        int i3 = (i2 >>> 6) + i;
        jArr[i3] = (1 << (i2 & 63)) ^ jArr[i3];
    }

    private static void a(long j, long[] jArr, int i, long[] jArr2, int i2) {
        if ((1 & j) != 0) {
            a(jArr2, i2, jArr, 0, i);
        }
        int i3 = 1;
        while (true) {
            j >>>= 1;
            if (j != 0) {
                if ((1 & j) != 0) {
                    long b = b(jArr2, i2, jArr, 0, i, i3);
                    if (b != 0) {
                        int i4 = i2 + i;
                        jArr2[i4] = b ^ jArr2[i4];
                    }
                }
                i3++;
            } else {
                return;
            }
        }
    }

    public LongArray a(LongArray longArray, int i, int[] iArr) {
        int c = c();
        if (c == 0) {
            return this;
        }
        int c2 = longArray.c();
        if (c2 == 0) {
            return longArray;
        }
        if (c <= c2) {
            LongArray longArray2 = longArray;
            longArray = this;
            this = longArray2;
            int i2 = c2;
            c2 = c;
            c = i2;
        }
        int i3 = (c2 + 63) >>> 6;
        int i4 = (c + 63) >>> 6;
        int i5 = ((c2 + c) + 62) >>> 6;
        if (i3 == 1) {
            long j = longArray.g[0];
            if (j == 1) {
                return this;
            }
            long[] jArr = new long[i5];
            a(j, this.g, i4, jArr, 0);
            return a(jArr, 0, i5, i, iArr);
        }
        int i6;
        int i7 = ((c + 7) + 63) >>> 6;
        int[] iArr2 = new int[16];
        long[] jArr2 = new long[(i7 << 4)];
        iArr2[1] = i7;
        System.arraycopy(this.g, 0, jArr2, i7, i4);
        i4 = i7;
        for (i6 = 2; i6 < 16; i6++) {
            i4 += i7;
            iArr2[i6] = i4;
            if ((i6 & 1) == 0) {
                a(jArr2, i4 >>> 1, jArr2, i4, i7, 1);
            } else {
                a(jArr2, i7, jArr2, i4 - i7, jArr2, i4, i7);
            }
        }
        long[] jArr3 = new long[jArr2.length];
        a(jArr2, 0, jArr3, 0, jArr2.length, 4);
        long[] jArr4 = longArray.g;
        long[] jArr5 = new long[(i5 << 3)];
        for (c2 = 0; c2 < i3; c2++) {
            long j2 = jArr4[c2];
            int i8 = c2;
            while (true) {
                j2 >>>= 4;
                i6 = ((int) j2) & 15;
                b(jArr5, i8, jArr2, iArr2[((int) j2) & 15], jArr3, iArr2[i6], i7);
                j2 >>>= 4;
                if (j2 == 0) {
                    break;
                }
                i8 += i5;
            }
        }
        int length = jArr5.length;
        while (true) {
            length -= i5;
            if (length == 0) {
                return a(jArr5, 0, i5, i, iArr);
            }
            b(jArr5, length - i5, jArr5, length, i5, 8);
        }
    }

    private static LongArray a(long[] jArr, int i, int i2, int i3, int[] iArr) {
        return new LongArray(jArr, i, b(jArr, i, i2, i3, iArr));
    }

    private static int b(long[] jArr, int i, int i2, int i3, int[] iArr) {
        int i4 = (i3 + 63) >>> 6;
        if (i2 < i4) {
            return i2;
        }
        int min = Math.min(i2 << 6, (i3 << 1) - 1);
        int i5 = (i2 << 6) - min;
        int i6 = i2;
        while (i5 >= 64) {
            i6--;
            i5 -= 64;
        }
        int length = iArr.length;
        int i7 = iArr[length - 1];
        length = length > 1 ? iArr[length - 2] : 0;
        int max = Math.max(i3, i7 + 64);
        length = (Math.min(min - max, i3 - length) + i5) >> 6;
        if (length > 1) {
            i5 = i6 - length;
            b(jArr, i, i6, i5, i3, iArr);
            while (i6 > i5) {
                i6--;
                jArr[i + i6] = 0;
            }
            length = i5 << 6;
        } else {
            length = min;
        }
        if (length > max) {
            a(jArr, i, i6, max, i3, iArr);
            length = max;
        }
        if (length > i3) {
            c(jArr, i, length, i3, iArr);
        }
        return i4;
    }

    private static void c(long[] jArr, int i, int i2, int i3, int[] iArr) {
        while (true) {
            i2--;
            if (i2 < i3) {
                return;
            }
            if (a(jArr, i, i2)) {
                d(jArr, i, i2, i3, iArr);
            }
        }
    }

    private static void d(long[] jArr, int i, int i2, int i3, int[] iArr) {
        b(jArr, i, i2);
        int i4 = i2 - i3;
        int length = iArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                b(jArr, i, iArr[length] + i4);
            } else {
                b(jArr, i, i4);
                return;
            }
        }
    }

    private static void a(long[] jArr, int i, int i2, int i3, int i4, int[] iArr) {
        long j;
        int i5 = i3 >>> 6;
        while (true) {
            i2--;
            if (i2 <= i5) {
                break;
            }
            j = jArr[i + i2];
            if (j != 0) {
                jArr[i + i2] = 0;
                a(jArr, i, i2 << 6, j, i4, iArr);
            }
        }
        int i6 = i3 & 63;
        j = jArr[i + i5] >>> i6;
        if (j != 0) {
            i5 += i;
            jArr[i5] = jArr[i5] ^ (j << i6);
            a(jArr, i, i3, j, i4, iArr);
        }
    }

    private static void a(long[] jArr, int i, int i2, long j, int i3, int[] iArr) {
        int i4 = i2 - i3;
        int length = iArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                a(jArr, i, iArr[length] + i4, j);
            } else {
                a(jArr, i, i4, j);
                return;
            }
        }
    }

    private static void b(long[] jArr, int i, int i2, int i3, int i4, int[] iArr) {
        int i5 = (i3 << 6) - i4;
        int length = iArr.length;
        while (true) {
            int i6 = length - 1;
            if (i6 >= 0) {
                d(jArr, i, jArr, i + i3, i2 - i3, i5 + iArr[i6]);
                length = i6;
            } else {
                d(jArr, i, jArr, i + i3, i2 - i3, i5);
                return;
            }
        }
    }

    private static void d(long[] jArr, int i, long[] jArr2, int i2, int i3, int i4) {
        int i5 = i + (i4 >>> 6);
        int i6 = i4 & 63;
        if (i6 == 0) {
            a(jArr, i5, jArr2, i2, i3);
            return;
        }
        int i7 = 64 - i6;
        jArr[i5] = c(jArr, i5 + 1, jArr2, i2, i3, i7) ^ jArr[i5];
    }

    public LongArray a(int i, int[] iArr) {
        int b = b();
        if (b == 0) {
            return this;
        }
        int i2 = b << 1;
        long[] jArr = new long[i2];
        b = 0;
        while (b < i2) {
            long j = this.g[b >>> 1];
            int i3 = b + 1;
            jArr[b] = d((int) j);
            b = i3 + 1;
            jArr[i3] = d((int) (j >>> 32));
        }
        this(jArr, 0, b(jArr, 0, jArr.length, i, iArr));
        return this;
    }

    private static long d(int i) {
        return (((long) (b[i & 255] | (b[(i >>> 8) & 255] << 16))) & 4294967295L) | ((((long) (b[(i >>> 16) & 255] | (b[i >>> 24] << 16))) & 4294967295L) << 32);
    }

    public LongArray b(int i, int[] iArr) {
        int i2 = 1;
        if (c() == 1) {
            return this;
        }
        LongArray longArray = (LongArray) clone();
        int i3 = (i + 63) >>> 6;
        d(new LongArray(i3).g, 0, i, i, iArr);
        new LongArray(i3).g[0] = 1;
        LongArray longArray2 = new LongArray(i3);
        int[] iArr2 = new int[]{r1, i + 1};
        LongArray[] longArrayArr = new LongArray[]{longArray, r4};
        int[] iArr3 = new int[]{1, 0};
        LongArray[] longArrayArr2 = new LongArray[]{r5, longArray2};
        i3 = iArr2[1];
        int i4 = iArr3[1];
        int i5 = i3 - iArr2[0];
        while (true) {
            if (i5 < 0) {
                i5 = -i5;
                iArr2[i2] = i3;
                iArr3[i2] = i4;
                i2 = 1 - i2;
                i3 = iArr2[i2];
                i4 = iArr3[i2];
            }
            longArrayArr[i2].a(longArrayArr[1 - i2], iArr2[1 - i2], i5);
            int b = longArrayArr[i2].b(i3);
            if (b == 0) {
                return longArrayArr2[1 - i2];
            }
            int i6 = iArr3[1 - i2];
            longArrayArr2[i2].a(longArrayArr2[1 - i2], i6, i5);
            i6 += i5;
            if (i6 > i4) {
                i4 = i6;
            } else if (i6 == i4) {
                i4 = longArrayArr2[i2].b(i4);
            }
            i5 += b - i3;
            i3 = b;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LongArray)) {
            return false;
        }
        LongArray longArray = (LongArray) obj;
        int b = b();
        if (longArray.b() != b) {
            return false;
        }
        for (int i = 0; i < b; i++) {
            if (this.g[i] != longArray.g[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int b = b();
        int i = 1;
        for (int i2 = 0; i2 < b; i2++) {
            long j = this.g[i2];
            i = (((i * 31) ^ ((int) j)) * 31) ^ ((int) (j >>> 32));
        }
        return i;
    }

    public Object clone() {
        return new LongArray(Arrays.a(this.g));
    }

    public String toString() {
        int b = b();
        if (b == 0) {
            return "0";
        }
        b--;
        StringBuffer stringBuffer = new StringBuffer(Long.toBinaryString(this.g[b]));
        while (true) {
            b--;
            if (b < 0) {
                return stringBuffer.toString();
            }
            String toBinaryString = Long.toBinaryString(this.g[b]);
            int length = toBinaryString.length();
            if (length < 64) {
                stringBuffer.append("0000000000000000000000000000000000000000000000000000000000000000".substring(length));
            }
            stringBuffer.append(toBinaryString);
        }
    }
}
