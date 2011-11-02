def contains_course(course_list, course_id):
    for course in course_list:
        if course.getId() == int(course_id):
            print True
            return True
    return False

def set_course_choices():
    course_choices = []
    for course in course_list:
        course_choices.append([course.id, course.name])
    return course_choices