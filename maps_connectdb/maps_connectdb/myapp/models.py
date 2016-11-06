# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from __future__ import unicode_literals

from django.db import models

class DjangoMigrations(models.Model):
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class Events(models.Model):
    starttime = models.DateTimeField(db_column='startTime', blank=True, null=True)  # Field name made lowercase.
    endtime = models.DateTimeField(db_column='endTime', blank=True, null=True)  # Field name made lowercase.
    latitude = models.FloatField(db_column='latitude', blank=True, null=True)
    longitude = models.FloatField(db_column='longitude', blank=True, null=True)
    rating = models.FloatField(db_column='rating', blank=True, null=True)
    salestarttime = models.DateTimeField(db_column='saleStartTime', blank=True, null=True)  # Field name made lowercase.
    saleendtime = models.DateTimeField(db_column='saleEndTime', blank=True, null=True)  # Field name made lowercase.
    eventname = models.CharField(db_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    eventid = models.CharField(db_column='eventID', primary_key=True, max_length=255)  # Field name made lowercase.
    subgenre = models.CharField(db_column='subgenre', max_length=255, blank=True, null=True)
    genre = models.CharField(db_column='genre', max_length=255, blank=True, null=True)
    type = models.CharField(db_column='type', max_length=255, blank=True, null=True)
    urlimg = models.CharField(db_column='urlImg', max_length=255, blank=True, null=True)  # Field name made lowercase.
    urlevent = models.CharField(db_column='urlEvent', max_length=255, blank=True, null=True)  # Field name made lowercase.
    urlbooking = models.CharField(db_column='urlBooking', max_length=255, blank=True, null=True)  # Field name made lowercase.
    source = models.CharField(db_column='source', max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'events'


class History(models.Model):
    historyid = models.CharField(db_column='historyid', primary_key=True, max_length=255)
    #historyid = models.AutoField()
    eventid = models.ForeignKey(Events, models.DO_NOTHING, db_column='eventID', blank=True, null=True)  # Field name made lowercase.
    invited = models.TextField(db_column='invited', blank=True, null=True)  # This field type is a guess.
    acceptedinvites = models.TextField(db_column='acceptedInvites', blank=True, null=True)  # Field name made lowercase. This field type is a guess.
    declinedinvite = models.TextField(db_column='declinedInvite', blank=True, null=True)  # Field name made lowercase. This field type is a guess.
    startlocations = models.TextField(db_column='startlocations', blank=True, null=True)  # This field type is a guess.

    class Meta:
        managed = False
        db_table = 'history'


class User(models.Model):
    userid = models.CharField(db_column='userid', primary_key=True, max_length=255)
    fbname = models.CharField(db_column='fbname', max_length=255, blank=True, null=True)
    username = models.CharField(db_column='username', max_length=255, blank=True, null=True)
    interests = models.TextField(db_column='interests', blank=True, null=True)  # This field type is a guess.
    transportationpref = models.CharField(db_column='transportationPref', max_length=255, blank=True, null=True)  # Field name made lowercase.
    homelat = models.FloatField(db_column='homelat', blank=True, null=True)
    homelong = models.FloatField(db_column='homelong', blank=True, null=True)
    officelat = models.FloatField(db_column='officelat', blank=True, null=True)
    officelong = models.FloatField(db_column='officelong', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'user'

