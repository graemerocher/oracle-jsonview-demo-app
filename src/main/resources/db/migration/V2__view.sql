CREATE OR REPLACE JSON RELATIONAL DUALITY VIEW "STUDENT_SCHEDULE" AS -- <1>
SELECT JSON{
        'studentId': s."ID", -- <2>
        'student': s."NAME" WITH UPDATE, -- <3>
        'averageGrade': s."AVERAGE_GRADE" WITH UPDATE,
        'schedule': [SELECT JSON{'id': sc."ID", -- <4>
                                 'course': (SELECT JSON{'courseId': c."ID", -- <5>
                                                       'teacher': (SELECT JSON{'teacherId': t."ID", -- <6>
                                                                                'teacher': t."NAME"}
                                                                    FROM "TEACHER" t WITH UPDATE WHERE c."TEACHER_ID" = t."ID"),
                                                       'room': c."ROOM",
                                                       'time': c."TIME",
                                                       'name': c."NAME" WITH UPDATE}
                                           FROM "COURSE" c WITH UPDATE WHERE sc."COURSE_ID" = c."ID")}
                      FROM "STUDENT_COURSE" sc WITH INSERT UPDATE DELETE WHERE s."ID" = sc."STUDENT_ID"]}
FROM "STUDENT" s WITH UPDATE INSERT DELETE;