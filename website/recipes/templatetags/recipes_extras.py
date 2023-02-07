from django.template import Library

register = Library()


@register.filter
def times(count):
    return range(int(count or 0))
