package com.matrusineh.health.data.model

data class WeeklyMilestone(
    val week: Int,
    val descriptionEn: String,
    val descriptionKn: String
)

object MilestoneData {
    val milestones = listOf(
        WeeklyMilestone(8, "Baby is the size of a kidney bean. Heart is beating!", "ಮಗು ಮೂತ್ರಪಿಂಡ ಹಣ್ಣಿನ ಗಾತ್ರದಷ್ಟಿದೆ. ಹೃದಯ ಬಡಿಯುತ್ತಿದೆ!"),
        WeeklyMilestone(9, "Baby's muscles are forming. Tiny movements begin.", "ಮಗುವಿನ ಸ್ನಾಯುಗಳು ರೂಪುಗೊಳ್ಳುತ್ತಿವೆ."),
        WeeklyMilestone(10, "Baby is the size of a strawberry. Vital organs are developing.", "ಮಗು ಸ್ಟ್ರಾಬೆರ್ರಿ ಗಾತ್ರದಷ್ಟಿದೆ. ಮುಖ್ಯ ಅಂಗಗಳು ಬೆಳೆಯುತ್ತಿವೆ."),
        WeeklyMilestone(11, "Baby can now swallow. Fingernails are growing.", "ಮಗು ನುಂಗಬಲ್ಲದು. ಉಗುರುಗಳು ಬೆಳೆಯುತ್ತಿವೆ."),
        WeeklyMilestone(12, "Baby is the size of a lime. Fingers are forming.", "ಮಗು ನಿಂಬೆ ಗಾತ್ರದಷ್ಟಿದೆ. ಬೆರಳುಗಳು ರೂಪುಗೊಳ್ಳುತ್ತಿವೆ."),
        WeeklyMilestone(13, "Baby can make facial expressions now.", "ಮಗು ಮುಖದ ಅಭಿವ್ಯಕ್ತಿಗಳನ್ನು ತೋರಿಸಬಲ್ಲದು."),
        WeeklyMilestone(14, "Baby is the size of a lemon. Arms are fully formed.", "ಮಗು ನಿಂಬೆ ಹಣ್ಣಿನ ಗಾತ್ರ. ತೋಳುಗಳು ಪೂರ್ಣ ರೂಪ ಪಡೆದಿವೆ."),
        WeeklyMilestone(15, "Baby can sense light through the eyelids.", "ಮಗು ಕಣ್ಣಿನ ರೆಪ್ಪೆಗಳ ಮೂಲಕ ಬೆಳಕನ್ನು ಅನುಭವಿಸಬಲ್ಲದು."),
        WeeklyMilestone(16, "Baby is the size of an avocado. Ears are developed — it can hear!", "ಮಗು ಅವಕಾಡೋ ಗಾತ್ರ. ಕಿವಿಗಳು ಅಭಿವೃದ್ಧಿ ಹೊಂದಿವೆ!"),
        WeeklyMilestone(17, "Baby's skeleton is hardening from cartilage to bone.", "ಮಗುವಿನ ಅಸ್ಥಿಪಂಜರ ಗಟ್ಟಿಯಾಗುತ್ತಿದೆ."),
        WeeklyMilestone(18, "Baby is the size of a sweet potato. It is very active!", "ಮಗು ಸಿಹಿಗೆಣಸಿನ ಗಾತ್ರ. ತುಂಬಾ ಚಟುವಟಿಕೆಯಿಂದಿದೆ!"),
        WeeklyMilestone(19, "Baby has fine hair (lanugo) covering the body.", "ಮಗುವಿನ ದೇಹದ ಮೇಲೆ ಸೂಕ್ಷ್ಮ ಕೂದಲು ಬೆಳೆದಿದೆ."),
        WeeklyMilestone(20, "Baby can hear your voice now! Halfway there!", "ಮಗು ನಿಮ್ಮ ದನಿ ಕೇಳಬಲ್ಲದು! ಅರ್ಧ ದಾರಿ ಬಂದಿದೆ!"),
        WeeklyMilestone(21, "Baby is the size of a banana. It loves to kick!", "ಮಗು ಬಾಳೆಹಣ್ಣಿನ ಗಾತ್ರ. ಒದೆಯಲು ಇಷ್ಟಪಡುತ್ತಿದೆ!"),
        WeeklyMilestone(22, "Baby's lips, eyelids, and eyebrows are distinct.", "ಮಗುವಿನ ತುಟಿ, ರೆಪ್ಪೆ ಮತ್ತು ಹುಬ್ಬುಗಳು ಸ್ಪಷ್ಟವಾಗಿವೆ."),
        WeeklyMilestone(23, "Baby can sense movement — it feels you move!", "ಮಗು ಚಲನೆ ಅನುಭವಿಸಬಲ್ಲದು — ನಿಮ್ಮ ಚಲನೆ ಅನುಭವಿಸುತ್ತಿದೆ!"),
        WeeklyMilestone(24, "Baby is the size of an ear of corn. Footprints and fingerprints forming.", "ಮಗು ಜೋಳದ ತೆನೆ ಗಾತ್ರ. ಹೆಜ್ಜೆ ಮತ್ತು ಬೆರಳ ಗುರುತು ರೂಪುಗೊಳ್ಳುತ್ತಿವೆ."),
        WeeklyMilestone(25, "Baby starts to gain fat. Skin is becoming smoother.", "ಮಗು ಕೊಬ್ಬು ಹೆಚ್ಚಿಸಿಕೊಳ್ಳುತ್ತಿದೆ. ಚರ್ಮ ನಯವಾಗುತ್ತಿದೆ."),
        WeeklyMilestone(26, "Baby's eyes can now open and close!", "ಮಗುವಿನ ಕಣ್ಣುಗಳು ತೆರೆಯಲು ಮತ್ತು ಮುಚ್ಚಲು ಶುರುವಾಗಿವೆ!"),
        WeeklyMilestone(27, "Baby can recognize your voice and responds to music.", "ಮಗು ನಿಮ್ಮ ದನಿ ಗುರುತಿಸಿ ಸಂಗೀತಕ್ಕೆ ಪ್ರತಿಕ್ರಿಯಿಸುತ್ತಿದೆ."),
        WeeklyMilestone(28, "Baby opens and closes eyes. Brain wave activity begins.", "ಮಗು ಕಣ್ಣು ತೆರೆಯುತ್ತದೆ. ಮಿದುಳಿನ ಚಟುವಟಿಕೆ ಆರಂಭ."),
        WeeklyMilestone(29, "Baby can dream! REM sleep starts now.", "ಮಗು ಕನಸು ಕಾಣಬಲ್ಲದು! REM ನಿದ್ದೆ ಆರಂಭ."),
        WeeklyMilestone(30, "Baby is the size of a cabbage. Lungs maturing rapidly.", "ಮಗು ಎಲೆಕೋಸಿನ ಗಾತ್ರ. ಶ್ವಾಸಕೋಶ ವೇಗವಾಗಿ ಪಕ್ವಗೊಳ್ಳುತ್ತಿದೆ."),
        WeeklyMilestone(31, "Baby is gaining weight fast — about half a pound a week!", "ಮಗು ವೇಗವಾಗಿ ತೂಕ ಹೆಚ್ಚಿಸಿಕೊಳ್ಳುತ್ತಿದೆ!"),
        WeeklyMilestone(32, "Baby's toenails are fully formed. All five senses working!", "ಮಗುವಿನ ಕಾಲ್ಬೆರಳ ಉಗುರುಗಳು ಸಂಪೂರ್ಣ. ಐದೂ ಇಂದ್ರಿಯಗಳು ಕೆಲಸ ಮಾಡುತ್ತಿವೆ!"),
        WeeklyMilestone(33, "Baby is sleeping and waking on a regular schedule.", "ಮಗು ನಿಯಮಿತ ವೇಳಾಪಟ್ಟಿಯಲ್ಲಿ ನಿದ್ದೆ ಮತ್ತು ಎಚ್ಚರ."),
        WeeklyMilestone(34, "Baby's lungs and central nervous system are nearly complete.", "ಮಗುವಿನ ಶ್ವಾಸಕೋಶ ಮತ್ತು ನರಮಂಡಲ ಬಹುತೇಕ ಸಂಪೂರ್ಣ."),
        WeeklyMilestone(35, "Baby is the size of a honeydew melon. Kidneys fully developed.", "ಮಗು ಕಲ್ಲಂಗಡಿ ಗಾತ್ರ. ಮೂತ್ರಪಿಂಡಗಳು ಪೂರ್ಣ ಅಭಿವೃದ್ಧಿ."),
        WeeklyMilestone(36, "Baby is almost ready! Most organs fully developed.", "ಮಗು ಬಹುತೇಕ ತಯಾರಾಗಿದೆ! ಹೆಚ್ಚಿನ ಅಂಗಗಳು ಸಂಪೂರ್ಣ."),
        WeeklyMilestone(37, "Baby is full-term! Head may be engaged in the pelvis.", "ಮಗು ಪೂರ್ಣ ಅವಧಿ! ತಲೆ ಶ್ರೋಣಿಯಲ್ಲಿ ನಿಲ್ಲಬಹುದು."),
        WeeklyMilestone(38, "Baby is adding fat to stay warm after birth.", "ಮಗು ಹುಟ್ಟಿದ ನಂತರ ಬೆಚ್ಚಗಿರಲು ಕೊಬ್ಬು ಸಂಗ್ರಹಿಸುತ್ತಿದೆ."),
        WeeklyMilestone(39, "Baby is the size of a watermelon and ready to meet you!", "ಮಗು ಕಲ್ಲಂಗಡಿ ಗಾತ್ರ ಮತ್ತು ನಿಮ್ಮನ್ನು ಭೇಟಿಯಾಗಲು ಸಿದ್ಧ!"),
        WeeklyMilestone(40, "Your baby is here! Congratulations! Take care and rest well.", "ನಿಮ್ಮ ಮಗು ಬಂದಿದೆ! ಅಭಿನಂದನೆ! ಚೆನ್ನಾಗಿ ವಿಶ್ರಾಂತಿ ತೆಗೆಕೊಳ್ಳಿ.")
    )

    fun getMilestone(week: Int): WeeklyMilestone? {
        return milestones.find { it.week == week }
            ?: milestones.minByOrNull { kotlin.math.abs(it.week - week) }
    }
}
