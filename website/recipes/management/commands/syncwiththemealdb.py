from django.core.management.base import BaseCommand

from ...utils import themealdb_synchronizer


class Command(BaseCommand):
    help = 'Synchronizes meals with TheMealDB via its API'

    def handle(self, *args, **options):
        themealdb_synchronizer.synchronize()
